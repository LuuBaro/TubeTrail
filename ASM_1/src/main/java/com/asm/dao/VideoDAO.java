package com.asm.dao;



import java.sql.Connection;
import java.util.ArrayList;
import java.util.List;



import com.asm.entity.Video;
import com.asm.utils.JpaUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityTransaction;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.TypedQuery;

public class VideoDAO {
	EntityManager entityManager = JpaUtil.getEntityManager();
	private int noOfRecords;

	public void create(Video video) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(video);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
	}

	public void update(Video video) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(video);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
	}

	public void remove(Video video) {
	    try {
	        entityManager.getTransaction().begin();
	        video = entityManager.find(Video.class, video.getId()); // Ensure the entity is managed
	        if (video != null) {
	            entityManager.remove(video);
	        }
	        entityManager.getTransaction().commit();
	    } catch (Exception e) {
	        if (entityManager.getTransaction().isActive()) {
	            entityManager.getTransaction().rollback();
	        }
	        e.printStackTrace();
	        throw new RuntimeException("Error removing video", e);
	    }
	}


//	public Video findById(String id) {
//		return entityManager.find(Video.class, id);
//	}
	
	
	
	public Video findById(Long videoId) {
	    EntityManager em = JpaUtil.getEntityManager();
	    try {
	        return em.find(Video.class, videoId);
	    } finally {
	        em.close();
	    }
	}
	
	public Video findById(String videoId) {
	    if (videoId == null || videoId.trim().isEmpty()) {
	        throw new IllegalArgumentException("ID to load is required for loading");
	    }

	    String jpql = "SELECT v FROM Video v WHERE v.id = :videoId";
	    TypedQuery<Video> query = entityManager.createQuery(jpql, Video.class);
	    query.setParameter("videoId", videoId);
	    List<Video> result = query.getResultList();
	    return result.isEmpty() ? null : result.get(0); // Trả về null nếu không tìm thấy video
	}



	public List<Video> findAll() {
		String jqpl = "Select v from Video v";
		TypedQuery<Video> query = entityManager.createQuery(jqpl, Video.class);
		return query.getResultList();
	}
	
	public List<Video> getVideosByPageQL(int pageNumber, int pageSize) {
		EntityManager em = JpaUtil.getEntityManager();
		List<Video> videos = null;
		try {
			int startPosition = (pageNumber - 1) * pageSize;
			TypedQuery<Video> query = em.createQuery("SELECT v FROM Video v", Video.class);
			query.setFirstResult(startPosition);
			query.setMaxResults(pageSize);
			videos = query.getResultList();
		} finally {
			em.close();
		}
		return videos;
	}

	public int getTotalVideoCountQL() {
		EntityManager em = JpaUtil.getEntityManager();
		long count = 0;
		try {
			count = (Long) em.createQuery("SELECT COUNT(v) FROM Video v").getSingleResult();
		} finally {
			em.close();
		}
		return (int) count;
	}

	public List<Video> getVideosByPage(int offset, int noOfRecords) {
		EntityManager em = JpaUtil.getEntityManager();
		String jpql = "SELECT v FROM Video v ORDER BY v.views DESC";
		TypedQuery<Video> query = em.createQuery(jpql, Video.class);
		query.setFirstResult(offset);
		query.setMaxResults(noOfRecords);
		return query.getResultList();
	}

	public int getTotalVideoCount() {
		EntityManager em = JpaUtil.getEntityManager();
		long count = (Long) em.createQuery("SELECT COUNT(v) FROM Video v").getSingleResult();
		return (int) count;
	}
	
	public List<Object[]> getVideoWithFavoriteCount() {
	    EntityManager em = JpaUtil.getEntityManager();
	    try {
	        String jpql = "SELECT v.id, v.title, v.views, COUNT(f.id), MAX(f.likeDate) "
	                    + "FROM Video v LEFT JOIN v.favorites f "
	                    + "GROUP BY v.id, v.title, v.views "
	                    + "ORDER BY v.title";
	        
	        TypedQuery<Object[]> query = em.createQuery(jpql, Object[].class);
	        return query.getResultList();
	    } finally {
	        em.close();
	    }
	}


	public List<Video> getAllVideos() {
        List<Video> videos = null;
        String query = "SELECT v FROM Video v";
        try {
            TypedQuery<Video> typedQuery = entityManager.createQuery(query, Video.class);
            videos = typedQuery.getResultList();
        } catch (PersistenceException e) {
            e.printStackTrace(); // Hoặc ghi log nếu cần
        }
        return videos;
    }

    public List<Video> getFavoriteVideos() {
        List<Video> videos = null;
        String query = "SELECT v FROM Video v WHERE v.id IN (SELECT f.video.id FROM Favorite f)";
        try {
            TypedQuery<Video> typedQuery = entityManager.createQuery(query, Video.class);
            videos = typedQuery.getResultList();
        } catch (PersistenceException e) {
            e.printStackTrace(); // Hoặc ghi log nếu cần
        }
        return videos;
    }


	public Video getVideo(String id) {
		Video video = null;
		EntityTransaction transaction = null;
		try {
			transaction = entityManager.getTransaction();
			transaction.begin();

			// Tìm video bằng ID
			video = entityManager.find(Video.class, id);

			transaction.commit();
		} catch (Exception e) {
			if (transaction != null && transaction.isActive()) {
				transaction.rollback();
			}
			e.printStackTrace();
		}
		return video;
	}

	public int getNoOfRecords() {
		return noOfRecords;
	}

	public List<Video> findAllSortedByViews(int offset, int noOfRecords) {
		EntityManager em = JpaUtil.getEntityManager();
		String jqpl = "SELECT v FROM Video v ORDER BY v.views DESC";
		TypedQuery<Video> query = em.createQuery(jqpl, Video.class);
		query.setFirstResult(offset);
		query.setMaxResults(noOfRecords);
		List<Video> videos = query.getResultList();

		TypedQuery<Long> countQuery = em.createQuery("SELECT COUNT(v) FROM Video v", Long.class);
		noOfRecords = countQuery.getSingleResult().intValue();

		return videos;
	}
	
	public void incrementViewCount(String videoId) {
        try {
            entityManager.getTransaction().begin();
            Video video = entityManager.find(Video.class, videoId);
            if (video != null) {
                video.setViews(video.getViews() + 1);
                entityManager.getTransaction().commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
            entityManager.getTransaction().rollback();
        }
    }
    


}

