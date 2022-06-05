package jpql;

import java.util.List;

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
			Team team = new Team();
			team.setName("teamA");
			em.persist(team);

			User user = new User();
			user.setUsername("member1");
			user.setAge(10);
			user.setTeam(team);

			em.persist(user);


			em.flush();
			em.clear();

			String query = "select m from User m left join m.team t on t.name =  'teamA'";
			List<User> result = em.createQuery(query, User.class)
				.getResultList();

			tx.commit();
		} catch (Exception e) {
			tx.rollback();
		} finally {
			em.close();
		}
		emf.close();
	}
}
