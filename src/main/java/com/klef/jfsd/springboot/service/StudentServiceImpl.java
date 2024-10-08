package com.klef.jfsd.springboot.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.klef.jfsd.springboot.model.Student;
import com.klef.jfsd.springboot.repository.StudentRepository;

@Service
public class StudentServiceImpl implements StudentService
{
	@Autowired
    private StudentRepository StudentRepository;
	@Override
	public String addstudent(Student student) {
		StudentRepository.save(student);
		return "You Are Added Successfully";
	}

	@Override
	public String updatestudent(Student std) {
		Student s = StudentRepository.findById(std.getId()).get();    
	    
	      s.setName(std.getName());
	      s.setDateofbirth(std.getDateofbirth());
	      s.setEmail(std.getEmail());
	      s.setPassword(std.getPassword());
	      s.setContact(std.getContact());
	    
	    StudentRepository.save(s);
	    
	    return "Student Updated Successfully";
		
	}

	@Override
	public Student viewstudentbyid(int sid) {
		Optional<Student> obj =  StudentRepository.findById(sid);
        
        if(obj.isPresent())
        {
          Student std = obj.get();
          
          return std;
        }
        else
        {
          return null;
        }
	}

	@Override
	public Student checkstdlogin(String email, String pwd) {
		
		return StudentRepository.checkstdlogin(email, pwd);
	}

	

	

}
