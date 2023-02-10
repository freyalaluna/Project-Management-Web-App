package edu.colostate.cs415.model;

import java.util.HashSet;
import java.util.Set;

import edu.colostate.cs415.dto.ProjectDTO;

public class Project {

	private String name;
	private ProjectSize size;
	private ProjectStatus status;
	private Set<Worker> workers;
	private Set<Qualification> qualifications;

	public Project(String name, Set<Qualification> qs, ProjectSize size) throws IllegalArgumentException {
		if (size == null) {
			throw new IllegalArgumentException();
		}
		this.name = name;
		this.qualifications = qs;
		// TODO: in new branch check for null for all other types
		this.size = size;
		this.status = ProjectStatus.PLANNED;
		this.workers = new HashSet<>();
	}

	@Override
	public boolean equals(Object other) {
		return false;
	}

	@Override
	public int hashCode() {
		if (name == null || name.isEmpty()) {
			return 0;
		}

		return name.hashCode();
	}

	@Override
	public String toString() {
		return null;
	}

	public String getName() {
		return this.name;
	}

	public ProjectSize getSize() {
		return this.size;
	}

	public ProjectStatus getStatus() {
		return status;
	}

	public void setStatus(ProjectStatus status) {
		this.status = status;
	}

	public void addWorker(Worker worker) {
		if(worker == null){
			throw new IllegalArgumentException();
		}
		this.workers.add(worker);
	}

	public void removeWorker(Worker worker) {
	}

	public Set<Worker> getWorkers() {
		return this.workers;
	}

	public void removeAllWorkers() {
	}

	public Set<Qualification> getRequiredQualifications() {
		return null;
	}

	public void addQualification(Qualification qualification) {
	}

	public Set<Qualification> getMissingQualifications() {
		return null;
	}

	public boolean isHelpful(Worker worker) {
		return false;
	}

	public ProjectDTO toDTO() {
		return null;
	}
}
