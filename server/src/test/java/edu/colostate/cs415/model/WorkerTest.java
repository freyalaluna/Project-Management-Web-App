package edu.colostate.cs415.model;

import java.util.HashSet;
import java.util.Set;
import java.lang.Integer;

import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

public class WorkerTest {
    private Worker worker;

    @Before
    public void setUp() throws Exception {
		Set<Qualification> qualifications = new HashSet<Qualification>();
        worker = new Worker("", qualifications, 0.0);
    }

    /***** TOSTRING *****/
    @Test
    public void testToString() {
       assertEquals("Worker.toString returns :0:0:0", worker.toString(), ":0:0:0");
    }

    @Test
    public void testToStringOnlyNameNotEmpty() {
       Set<Qualification> qualifications = new HashSet<Qualification>();
       Worker workerWithName = new Worker("test1", qualifications, 0.0);
       assertEquals(workerWithName.toString(), "test1:0:0:0");
    }

    @Test
    public void testToStringWithQuals() {
       Set<Qualification> qualifications = new HashSet<Qualification>();
       Qualification q = new Qualification("test");
       qualifications.add(q);
       Worker workerWithName = new Worker("test2", qualifications, 0.0);
       assertEquals(workerWithName.toString(), "test2:0:1:0");
    }

    @Test
    public void testToStringWithQualsAndNonZeroSalary() {
       Set<Qualification> qualifications = new HashSet<Qualification>();
       Qualification q = new Qualification("test");
       qualifications.add(q);
       Worker workerWithName = new Worker("test3", qualifications, 5122.1232);
       assertEquals(workerWithName.toString(), "test3:0:1:5122");
    }

    @Test
    public void testToStringWithOnlySalaryNotEmpty() {
       Set<Qualification> qualifications = new HashSet<Qualification>();
       Worker workerWithName = new Worker("", qualifications, 12345.9999);
       assertEquals(workerWithName.toString(), ":0:0:12345");
    }

    @Test
    public void testToStringWithIntSalary() {
       Set<Qualification> qualifications = new HashSet<Qualification>();
       Worker workerWithName = new Worker("", qualifications, 4);
       assertEquals(workerWithName.toString(), ":0:0:4");
    }

    // Test for displaying number of projects when addProjects is done.

    /***** HASHCODE *****/
	@Test
    public void testHashCodeWithNullString() {
        assertEquals( "Worker.hashCode returns 0 with a null string", worker.hashCode(), 0);
    }

	@Test
    public void testHashCodeWithEmptyString() {
        assertEquals( "Worker.hashCode returns 0 with an empty string", worker.hashCode(), 0);
    }

	@Test
    public void testHashCodeWithValidString() {
		Worker workerWithValidName = new Worker(("test"), null, 0);
        assertThat("Worker.hashCode returns a non 0 code with a valid name", workerWithValidName.hashCode(), is(not(0)));
    }

    /**** getName ****/
    @Test
    public void testGetNameWithEmptyName() {
        assertEquals(worker.getName(),"");
    }

    @Test
    public void testGetNameWithNonEmptyName() {
        Worker worker = new Worker("test", null, 0);
        assertEquals(worker.getName(), "test");
    }

    /*** getSalary - setSalary */
    @Test
    public void testGetSalary() {
        assertEquals(worker.getSalary(), 0.0, 0.001);
    }

    @Test
    public void testSetSalary() {
        double expectedSalary = 1000.99;
        worker.setSalary(expectedSalary);
        assertEquals(worker.getSalary(), expectedSalary, 0.001);
    }

    @Test
    public void testSetSalaryToZero() {
        double expectedSalary = 0;
        worker.setSalary(expectedSalary);
        assertEquals(worker.getSalary(), expectedSalary, 0.001);
    }

    @Test
    public void testSetSalaryToNegative() {
        double expectedSalary = -1234.12;
        worker.setSalary(expectedSalary);
        assertEquals(worker.getSalary(), expectedSalary, 0.001);
    }

    /*** getQualificiations */
    @Test
    public void testGetQualificationsReturnsEmptySet() {
        assertEquals(worker.getQualifications().size(), 0);
    }

    @Test
    public void testGetQualificationsReturnsCorrectNumberOfQuals() {
        Qualification q1 = new Qualification("q1");
        Qualification q2 = new Qualification("q2");
        Set<Qualification> qualifications = new HashSet<Qualification>();
        qualifications.add(q1);
        qualifications.add(q2);
        Worker workerWithQuals = new Worker("test", qualifications, 0);
        assertEquals(workerWithQuals.getQualifications().size(), 2);
    }

    /*** addQualifications */
    @Test
    public void testAddQualificationsReturnsCorrectNumberOfQuals() {
        Qualification q1 = new Qualification("q1");
        Qualification q2 = new Qualification("q2");
        Set<Qualification> qualifications = new HashSet<Qualification>();
        Worker workerWithQuals = new Worker("test", qualifications, 0);
        workerWithQuals.addQualification(q1);
        workerWithQuals.addQualification(q2);
        assertEquals(workerWithQuals.getQualifications().size(), 2);
    }

    @Test
    public void testAddQualificationsWithDuplicateQualsReturnsCorrectNumberOfQuals() {
        Qualification q1 = new Qualification("q1");
        Set<Qualification> qualifications = new HashSet<Qualification>();
        Worker workerWithQuals = new Worker("test", qualifications, 0);
        workerWithQuals.addQualification(q1);
        workerWithQuals.addQualification(q1);
        assertEquals(workerWithQuals.getQualifications().size(), 1);
    }

    @Test
    public void testAddQualificationsWithTwoDuplicateQualsReturnsCorrectNumberOfQuals() {
        Qualification q1 = new Qualification("q1");
        Qualification q2 = new Qualification("q2");
        Set<Qualification> qualifications = new HashSet<Qualification>();
        Worker workerWithQuals = new Worker("test", qualifications, 0);
        workerWithQuals.addQualification(q1);
        workerWithQuals.addQualification(q1);
        workerWithQuals.addQualification(q2);
        workerWithQuals.addQualification(q2);
        assertEquals(workerWithQuals.getQualifications().size(), 2);
    }

    /*** getProjects - addProjects */
    @Test
    public void testGetProjectsReturnsEmptySet(){
        assertEquals(worker.getProjects().size(), 0);
    }

    @Test
    public void testGetProjectsReturnsCorrectNumberOfProjects(){
        Set<Qualification> qualifications = new HashSet<Qualification>();
        Project proj = new Project("p1", qualifications, ProjectSize.BIG);
        worker.addProject(proj);
        assertEquals(worker.getProjects().size(), 1);
    }

    @Test
    public void testAddingDuplicateProjects(){
        Set<Qualification> qualifications = new HashSet<Qualification>();
        Project proj = new Project("p1", qualifications, ProjectSize.BIG);
        worker.addProject(proj);
        worker.addProject(proj);
        assertEquals(worker.getProjects().size(), 1);
    }

    @Test
    public void testWorkerDoesntLimitAddingProjects(){
        int numberSmallProjects = 13;
        Set<Qualification> qualifications = new HashSet<Qualification>();
        for(Integer i = 0; i < numberSmallProjects; i++){
            String projectName = "p" + i.toString();
            Project p = new Project(projectName, qualifications, ProjectSize.BIG);
            worker.addProject(p);
        }
        assertEquals(worker.getProjects().size(), numberSmallProjects);
    }

    

}
