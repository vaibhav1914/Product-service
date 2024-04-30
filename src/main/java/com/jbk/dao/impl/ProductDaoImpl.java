package com.jbk.dao.impl;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.jbk.dao.ProductDao;
import com.jbk.entity.ProductEntity;
import com.jbk.exceptions.ResourcesAlreadyExist;
import com.jbk.exceptions.SomethingWentWrong;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public boolean addProduct(ProductEntity productEntity) {
		boolean isAdded = false;
		try (Session session = sessionFactory.openSession()) {
			ProductEntity productByName = getProductByName(productEntity.getProductName());
			if (productByName == null) {
				session.save(productEntity);
				session.beginTransaction().commit();
				isAdded = true;
			} else {
				throw new ResourcesAlreadyExist("Product Already Exist");
			}

		} catch (ResourcesAlreadyExist e) {
			throw new ResourcesAlreadyExist(e.getMessage());

		}

		catch (Exception e) {
			throw new SomethingWentWrong("Something went wrong during Adding Product");

		}
		return isAdded;

	}

	@Override
	public ProductEntity getProductByName(String productName) {
		try (Session session = sessionFactory.openSession()) {
			Criteria criteria = session.createCriteria(ProductEntity.class);
			criteria.add(Restrictions.eq("productName", productName));
			ProductEntity productEntity = (ProductEntity) criteria.uniqueResult();
			if (productEntity != null) {
				return productEntity;
			}
		} catch (Exception e) {
			throw new SomethingWentWrong("Something went wrong during getting Product By Name");
		}
		return null;
	}

	@Override
	public ProductEntity getProductById(long productId) {
		try (Session session = sessionFactory.openSession()) {
			ProductEntity productEntity = session.get(ProductEntity.class, productId);
			if (productEntity != null) {
				return productEntity;
			}
		} catch (Exception e) {
			throw new SomethingWentWrong("Something went wrong during getting Product By Id");

		}
		return null;
	}

}
