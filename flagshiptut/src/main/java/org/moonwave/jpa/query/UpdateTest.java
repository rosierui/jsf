package org.moonwave.jpa.query;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import org.moonwave.jpa.model.Role;
import org.moonwave.jpa.model.Semester;
import org.moonwave.jpa.model.SemesterWeek;
import org.moonwave.jpa.model.TutorGroup;
import org.moonwave.util.RandomUtil;

public class UpdateTest {

   public static void main( String[ ] args ) {
   
	 java.sql.Date date;
	 java.time.LocalDate date1;

      EntityManagerFactory emfactory = Persistence.createEntityManagerFactory( "jpa-mysql" );
      EntityManager em = emfactory.createEntityManager();
      System.out.println("");

      // TutorGroup
//      Query query = em.createQuery("Select e from TutorGroup e where e.alias = ?1", TutorGroup.class); //Okay
//      query.setParameter(1, "Listening & Oral 1");

      Query query = em.createQuery("Select e from TutorGroup e where e.alias = :alias", TutorGroup.class);
      query.setParameter("alias", "HSK");

      List<TutorGroup> list = (List<TutorGroup>) query.getResultList();

      for(TutorGroup e:list) {
         System.out.println("Tutor Group: "+ e.getAlias() + ", " + e.getName());
      }
      System.out.println("");
      if (!list.isEmpty()) {
          TutorGroup item = list.get(0);
          em.getTransaction().begin();
          item.setName("HSK-" + RandomUtil.nextPositiveInt());
          em.getTransaction().commit();
      }
      System.out.println("== Search again==");
      query = em.createQuery("Select e from TutorGroup e where e.alias = :alias", TutorGroup.class);
      query.setParameter("alias", "HSK");

      list = (List<TutorGroup>) query.getResultList();

      for(TutorGroup e:list) {
         System.out.println("Tutor Group: "+ e.getAlias() + ", " + e.getName());
      }
      System.out.println("");

      /*
      // Semester
      query = em.createQuery("Select e from Semester e");
      List<Semester> semesters = query.getResultList();

      for(Semester e: semesters) {
         System.out.println("Semester: "+ e.getAlias() + ", " + e.getName());
      }
      System.out.println("");

      // Role
      query = em.createQuery("Select e from Role e");
      List<Role> roles = query.getResultList();

      for(Role e: roles) {
         System.out.println("Role: "+ e.getAlias() + ", " + e.getName());
      }
      System.out.println("");

      // SemesterWeek
      query = em.createQuery("Select e from SemesterWeek e");
      List<SemesterWeek> semesterWeeks = query.getResultList();

      for(SemesterWeek e: semesterWeeks) {
         System.out.println("SemesterWeek: "+ e.getSemester() + ", " + e.getWeek());
      }
      */

   }
}