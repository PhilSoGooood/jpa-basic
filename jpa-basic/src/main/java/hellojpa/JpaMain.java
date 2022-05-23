package hellojpa;

import java.time.LocalDateTime;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class JpaMain {
	public static void main(String[] args) {
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

		EntityManager em = emf.createEntityManager();

		EntityTransaction tx = em.getTransaction();
		tx.begin();

		try {
			Member member = new Member();
			member.setUsername("phil");
			member.setHomeAddress(new Address("home", "street", "10000"));
			member.getFavoriteFoods().add("치킨");
			member.getFavoriteFoods().add("족발");
			member.getFavoriteFoods().add("피자");

			member.getAddressHistory().add(new AddressEntity("old1", "street", "10000"));
			member.getAddressHistory().add(new AddressEntity("old2", "street", "10000"));

			em.persist(member);
			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}
}
