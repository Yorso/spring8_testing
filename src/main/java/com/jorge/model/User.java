package com.jorge.model;

public class User {
	
		private Long id;
		private String firstName;
		private Integer age;
		
		public User(){
		}
		
		public User(String firstName, Integer age){
			this.firstName = firstName;
			this.age = age;
		}
		
		public Long getId() {
			return id;
		}
		public void setId(Long id) {
			this.id = id;
		}
		public String getFirstName() {
			return firstName;
		}
		public void setFirstName(String firstName) {
			this.firstName = firstName;
		}
		public Integer getAge() {
			return age;
		}
		public void setAge(Integer age) {
			this.age = age;
		}
		
}
