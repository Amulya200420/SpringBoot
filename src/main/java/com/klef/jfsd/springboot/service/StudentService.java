package com.klef.jfsd.springboot.service;




import com.klef.jfsd.springboot.model.Student;

public interface StudentService 
{
  public String addstudent(Student student);
  public String updatestudent(Student std);
  public Student viewstudentbyid(int sid);
  public Student checkstdlogin(String email,String pwd);

}
