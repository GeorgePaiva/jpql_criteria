package br.com.alura.jpa.testes;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Root;

import br.com.alura.jpa.modelo.Movimentacao;

public class TesteSomaDasMovimentacoes {

	public static void main(String[] args) {

		EntityManagerFactory emf = Persistence.createEntityManagerFactory("alura");
		EntityManager em = emf.createEntityManager();

		CriteriaBuilder builder = em.getCriteriaBuilder(); // Object que constr?i a query.
		CriteriaQuery<BigDecimal> query = builder.createQuery(BigDecimal.class);

		// O Root ? quem define qual entidade estamos buscando.
		// select m from Movimentacao m
		Root<Movimentacao> root = query.from(Movimentacao.class);

		// select sum(m.valor)
		Expression<BigDecimal> sum = builder.sum(root.<BigDecimal>get("valor"));
		query.select(sum);

		TypedQuery<BigDecimal> typedQuery = em.createQuery(query);

		System.out.println();
		System.out.println("A soma das movimenta??es ?: " + typedQuery.getSingleResult());

	}
}
