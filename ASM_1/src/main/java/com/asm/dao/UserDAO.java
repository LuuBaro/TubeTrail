package com.asm.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.asm.entity.*;
import com.asm.utils.JpaUtil;
import com.asm.*;
import com.asm.*;
import com.asm.entity.User;
import java.util.logging.Logger;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.NoResultException;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.Query;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.persistence.TypedQuery;
public class UserDAO {
	
	EntityManager entityManager = JpaUtil.getEntityManager();
	private static final Logger logger = Logger.getLogger(UserDAO.class.getName());
	public void create(User user) {
	    EntityTransaction transaction = entityManager.getTransaction();
	    try {
	        transaction.begin();
	        entityManager.persist(user);
	        transaction.commit();
	    } catch (Exception e) {
	        if (transaction.isActive()) {
	            transaction.rollback();
	        }
	        e.printStackTrace(); // In lỗi chi tiết để xác định nguyên nhân
	    }
	}

	public void update(User user) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(user);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			entityManager.getTransaction().rollback();
		}
	}
	public void sua(User userId) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(userId);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			entityManager.getTransaction().rollback();
		}
	}
	public void remove(User user) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(user);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			// TODO: handle exception
			entityManager.getTransaction().rollback();
		}
	}
	
	 public void delete(String userId) {
	        EntityTransaction transaction = entityManager.getTransaction();
	        try {
	            transaction.begin();
	            User user = entityManager.find(User.class, userId);
	            if (user != null) {
	                entityManager.remove(user);
	            }
	            transaction.commit();
	        } catch (Exception e) {
	            if (transaction.isActive()) {
	                transaction.rollback();
	            }
	            e.printStackTrace(); // In lỗi chi tiết để xác định nguyên nhân
	        }
	    }
	
	 
	 
//	 public List<User> getFavoriteUsersByVideoId(String videoId) {
//		    List<User> userFavoriteList = new ArrayList<>();
//		    String sql = "SELECT u.id, u.Fullname, u.email, f.LikeDate "
//		               + "FROM [User] u "
//		               + "JOIN Favorite f ON u.id = f.UserId "
//		               + "WHERE f.VideoId = :videoId";
//		    
//		    try {
//		        Query query = entityManager.createNativeQuery(sql);
//		        query.setParameter("videoId", videoId);
//		        List<Object[]> results = query.getResultList();
//		        
//		        System.out.println("Video ID: " + videoId);
//		        System.out.println("Number of results: " + results.size());
//		        
//		        Map<String, User> userMap = new HashMap<>();
//		        
//		        for (Object[] row : results) {
//		            String userId = (String) row[0];
//		            User user = userMap.get(userId);
//		            
//		            if (user == null) {
//		                user = new User();
//		                user.setId(userId);
//		                user.setFullName((String) row[1]);
//		                user.setEmail((String) row[2]);
//		                user.setFavorites(new ArrayList<>()); // Khởi tạo danh sách yêu thích
//		                userMap.put(userId, user);
//		            }
//		            
//		            Favorite favorite = new Favorite();
//		            favorite.setLikeDate((java.sql.Date) row[3]);
//		            favorite.setUser(user); // Thiết lập mối quan hệ ngược
//		            favorite.setVideo(new Video()); // Thiết lập video nếu cần
//		            
//		            user.getFavorites().add(favorite); // Thêm favorite vào danh sách yêu thích của người dùng
//		        }
//		        
//		        userFavoriteList.addAll(userMap.values());
//		        
//		    } catch (Exception e) {
//		        e.printStackTrace(); // Hoặc ghi log nếu cần
//		    }
//		    
//		    return userFavoriteList;
//		}





	    

	   
	 
	 
	 
	public User findById(String id) {
		return entityManager.find(User.class, id);
	}
	
	public List<User> getFavoriteUsers() {
	    // Giả sử bạn có một bảng hoặc mối quan hệ để lưu trữ người dùng yêu thích video
	    String jpql = "SELECT u FROM User u JOIN u.favorites f WHERE f.video IS NOT NULL"; // Thay đổi JPQL cho phù hợp với mô hình dữ liệu của bạn
	    TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
	    return query.getResultList();
	}

	public List<User> getFavoriteUsers(String searchKeyword) {
	    String jpql = "SELECT u FROM User u JOIN u.favorites f WHERE f.video IS NOT NULL AND (u.email LIKE :searchKeyword OR u.fullName LIKE :searchKeyword)";
	    TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
	    query.setParameter("searchKeyword", "%" + searchKeyword + "%");
	    return query.getResultList();
	}


	
	public User findById(Long userId) {
	    EntityManager em = JpaUtil.getEntityManager();
	    try {
	        return em.find(User.class, userId);
	    } finally {
	        em.close();
	    }
	}

	public List<User> findAll() {
		String jpql = "Select u from User u";
		TypedQuery<User> query = entityManager.createQuery(jpql, User.class);
		List<User> listUser = query.getResultList();	
		return listUser;
	}
	
	public List<User> findAll_V2() {
		TypedQuery<User> query = entityManager.createNamedQuery("findAllUser", User.class);
		List<User> listUser = query.getResultList();	
		return listUser;
	}
	
	public User findByUsernameAndPassword(String username, String password) {
	    try {
	        TypedQuery<User> query = entityManager.createQuery(
	            "SELECT u FROM User u WHERE u.username = :username AND u.password = :password", User.class);
	        query.setParameter("username", username);
	        query.setParameter("password", password);
	        return query.getSingleResult();
	    } catch (NoResultException e) {
	        return null;
	    }
	}

	
	public List<User> findByEmail(String email) {
	    String sql = "SELECT * FROM User WHERE email = :email";
	    Query query = entityManager.createNativeQuery(sql, User.class);
	    query.setParameter("email", email);
	    return query.getResultList();
	}


	public List<User> findByEmail_V2(String email) {
		
		Query query = entityManager.createNamedQuery("findUserByEmailSQL", User.class);
		query.setParameter(1, email);
		List<User> listUser = query.getResultList();	
		return listUser;
	}
	
	public List<User> findByEmail_V3(String email) {
		
		StoredProcedureQuery query = entityManager.createNamedStoredProcedureQuery("st_findUserByEmail");
		query.setParameter("email", email);
		List<User> listUser = query.getResultList();	
		return listUser;
	}
	
	
	
	//truy vấn của thầy
//	public List<Favorite> findUserFavoriteById(String id) {
//		User user = entityManager.find(User.class, id);
//		return user.getFavorites();
//	}
//	//truy van video dc yeu thich theo userid
//	//cach 1
//	public List<Video> findVideoFavoriteByUserId1(String userId) {
//		User user = entityManager.find(User.class, userId);
//		List<Favorite>favorites = user.getFavorites();
//		List<Video> videos = new ArrayList<Video>();
//		for (Favorite favorite : favorites) {
//			videos.add(favorite.getVideo());
//		}
//		return videos;
//	}
//	//cach 2
//	public List<Video> findVideoFavoriteByUserId2(String userId) {
//		String jpql = "Select f.video From Favorite f where f.user.id = :uid";
//		TypedQuery<Video> query = entityManager.createQuery(jpql, Video.class);
//		query.setParameter("uid", userId);
//		return query.getResultList();
//	}
}


