package com.asm.dao;

import java.util.List;

import com.asm.entity.Share;
import com.asm.utils.JpaUtil;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.persistence.TypedQuery;

public class ShareDAO {

	EntityManager entityManager = JpaUtil.getEntityManager();

	public void create(Share share) {
		try {
			entityManager.getTransaction().begin();
			entityManager.persist(share);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
	}

	public void update(Share share) {
		try {
			entityManager.getTransaction().begin();
			entityManager.merge(share);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
	}

	public void remove(Share share) {
		try {
			entityManager.getTransaction().begin();
			entityManager.remove(share);
			entityManager.getTransaction().commit();
		} catch (Exception e) {
			entityManager.getTransaction().rollback();
		}
	}

	public Share findById(Long id) {
	    return entityManager.find(Share.class, id);
	}

	
	public List<Share> getSharesByVideoId(Long videoId) {
	    String jpql = "SELECT s FROM Share s WHERE s.video.id = :videoId";
	    TypedQuery<Share> query = entityManager.createQuery(jpql, Share.class);
	    query.setParameter("videoId", videoId);
	    return query.getResultList();
	}

	

    
	
	
	
	public List<Share> getSharesByVideoId(String videoId) {
	   
	    List<Share> shares = entityManager.createQuery(
	        "SELECT s FROM Share s WHERE s.videoId = :videoId", Share.class)
	        .setParameter("videoId", videoId)
	        .getResultList();
	    entityManager.close();
	    return shares;
	}

	public List<Object[]> getShareDetailsByVideoId(Long videoId) {
	    String jpql = "SELECT u.fullName AS senderName, u.email AS senderEmail, s.emails AS recipientEmails, s.shareDate AS shareDate " +
	                 "FROM Share s " +
	                 "JOIN User u ON s.user.id = u.id " +
	                 "WHERE s.video.id = :videoId";
	    TypedQuery<Object[]> query = entityManager.createQuery(jpql, Object[].class);
	    query.setParameter("videoId", videoId);
	    return query.getResultList();
	}


	public List<Share> findAll() {
		String jqpl = "Select s from Share s";
		TypedQuery<Share> query = entityManager.createQuery(jqpl, Share.class);
		return query.getResultList();
	}

	
	public List<Share> findByVideoId(String videoId) {
	    TypedQuery<Share> query = entityManager.createQuery(
	        "SELECT s FROM Share s WHERE s.video.id = :videoId", Share.class);
	    query.setParameter("videoId", videoId);
	    return query.getResultList();
	}



	public List<Share> getSharesForVideo(long videoId) {
		TypedQuery<Share> query = entityManager.createQuery("SELECT s FROM Share s WHERE s.video.id = :videoId",
				Share.class);
		query.setParameter("videoId", videoId);
		return query.getResultList();
	}

	

}
