package mapper;

import java.util.ArrayList;
import java.util.List;

import dto.StudentDto;
import entity.Student;

public class EntityMapper {
	public static List<StudentDto> studentToDto(List<Student> students) {
		List<StudentDto> dtos = new ArrayList<StudentDto>();
		
		for (Student student : students) {
			StudentDto dto = new StudentDto(student.getId(), 
					student.getNome(), student.getCurso(), student.getFlagAtivo());
			dtos.add(dto);
		}
		
		return dtos;
	}
}
