package com.asm.dao;

import java.util.ArrayList;
import java.util.List;

import com.asm.entity.Favorite;
import com.asm.entity.User;
import com.asm.entity.Video;
import com.asm.utils.JpaUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class FavoriteDAO {
	private EntityManager entityManager;
	
	
	public FavoriteDAO() {
		this.entityManager = JpaUtil.getEntityManager();
	}

	
	

	public void create(Favorite favorite) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(favorite);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			e.printStackTrace(); // Log the exception
		}
	}

	public void update(Favorite favorite) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(favorite);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			if (entityManager.getTransaction().isActive()) {
				entityManager.getTransaction().rollback();
			}
			e.printStackTrace(); // Log the exception
		}
	}

//	public void remove(Favorite favorite) {
//		try {
//			entityManager.getTransaction().begin();
//			Favorite managedFavorite = entityManager.find(Favorite.class, favorite.getId());
//			if (managedFavorite != null) {
//				entityManager.remove(managedFavorite);
//				entityManager.getTransaction().commit();
//			}
//		} catch (Exception e) {
//			if (entityManager.getTransaction().isActive()) {
//				entityManager.getTransaction().rollback();
//			}
//			e.printStackTrace(); // Log the exception
//		}
//	}
	public boolean remove(Favorite favorite) {
        try {
            entityManager.getTransaction().begin();
            // Tìm kiếm Favorite dựa trên User và Video
            TypedQuery<Favorite> query = entityManager.createQuery(
                "SELECT f FROM Favorite f WHERE f.user = :user AND f.video = :video", Favorite.class);
            query.setParameter("user", favorite.getUser());
            query.setParameter("video", favorite.getVideo());

            Favorite managedFavorite = query.getSingleResult();
            if (managedFavorite != null) {
                entityManager.remove(managedFavorite);
                entityManager.getTransaction().commit();
                return true;
            }
        } catch (Exception e) {
            if (entityManager.getTransaction().isActive()) {
                entityManager.getTransaction().rollback();
            }
            e.printStackTrace(); // Log the exception
        }
        return false;
    }
	
	
	public Favorite findById(Long id) {
		return entityManager.find(Favorite.class, id);
	}

	
	public List<Video> getFavoriteVideos() {
	    List<Video> videos = null;
	    String query = "SELECT v FROM Video v WHERE v.id IN (SELECT f.videoId FROM Favorite f)";
	    
	    try {
	        TypedQuery<Video> typedQuery = entityManager.createQuery(query, Video.class);
	        videos = typedQuery.getResultList();
	    } catch (PersistenceException e) {
	        System.out.println("Error retrieving favorite videos: " + e.getMessage());
	        e.printStackTrace();
	    }
	    return videos;
	}


	
	public List<Favorite> findAll() {
		String jpql = "SELECT f FROM Favorite f";
		TypedQuery<Favorite> query = entityManager.createQuery(jpql, Favorite.class);
		return query.getResultList();
	}

	public List<Favorite> findByUserId(String userId) {
	    try {
	        String jpql = "SELECT f FROM Favorite f WHERE f.user.id = :userId";
	        TypedQuery<Favorite> query = entityManager.createQuery(jpql, Favorite.class);
	        query.setParameter("userId", userId);
	        return query.getResultList();
	    } catch (Exception e) {
	        e.printStackTrace();
	        return new ArrayList<>();
	    }
	}


	
	
//	public List<Object[]> findFavoriteSummary() {
//		// Truy vấn tổng hợp thông tin yêu thích
//		String jpql = "SELECT v.title AS VideoTitle, COUNT(f.id) AS FavoriteCount, "
//				+ "MAX(f.likeDate) AS LatestDate, MIN(f.likeDate) AS OldestDate " + "FROM Favorite f "
//				+ "JOIN f.video v " // Sử dụng mối quan hệ từ Favorite tới Video
//				+ "GROUP BY v.title";
//		Query query = entityManager.createQuery(jpql);
//		return query.getResultList();
//	}

	public List<User> findUsersByFavoriteVideo(String videoId) {
		String jpql = "SELECT u FROM User u JOIN u.favorites f WHERE f.video.id = :videoId";
		TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
		query.setParameter("videoId", videoId);
		return query.getResultList();
	}

	public List<Object[]> findFavoriteSummary() {
		String jpql = "SELECT v.title, COUNT(f.id), MAX(f.likeDate), MIN(f.likeDate) FROM Favorite f JOIN f.video v GROUP BY v.title";
		return entityManager.createQuery(jpql, Object[].class).getResultList();
	}
	
	public List<Favorite> findByVideoId(String videoId) {
        String jpql = "SELECT f FROM Favorite f WHERE f.video.id = :videoId";
        TypedQuery<Favorite> query = entityManager.createQuery(jpql, Favorite.class);
        query.setParameter("videoId", videoId);
        return query.getResultList();
    }
	
	public List<Favorite> getFavoritesByVideoId(String videoId) {
	    List<Favorite> favoriteList = new ArrayList<>();
	    String sql = "SELECT f.UserId, u.Fullname, u.Email, f.LikeDate "
	               + "FROM Favorite f "
	               + "JOIN [User] u ON f.UserId = u.Id "
	               + "WHERE f.VideoId = :videoId";

	    try {
	        Query query = entityManager.createNativeQuery(sql);
	        query.setParameter("videoId", videoId);
	        List<Object[]> results = query.getResultList();

	        System.out.println("Video ID: " + videoId);
	        System.out.println("Number of results: " + results.size());

	        for (Object[] row : results) {
	            Favorite favorite = new Favorite();
	            User user = new User();
	            
	            // Set thông tin người dùng
	            user.setId((String) row[0]);
	            user.setFullName((String) row[1]);
	            user.setEmail((String) row[2]);
	            
	            // Thiết lập đối tượng Favorite
	            favorite.setUser(user);
	            favorite.setLikeDate((java.sql.Date) row[3]);
	            
	            favoriteList.add(favorite);
	        }
	    } catch (Exception e) {
	        e.printStackTrace(); // Hoặc ghi log nếu cần
	    }

	    return favoriteList;
	}
	
	
	
	
	public List<Favorite> findByUserId(Integer userId) {
	    String jpql = "SELECT f FROM Favorite f WHERE f.user.id = :userId";
	    TypedQuery<Favorite> query = entityManager.createQuery(jpql, Favorite.class);
	    query.setParameter("userId", userId);
	    return query.getResultList();
	}

}
