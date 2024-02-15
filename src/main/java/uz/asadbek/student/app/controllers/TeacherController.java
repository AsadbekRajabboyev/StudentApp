/******************************************************************************
 * Copyright (c)  2/13/24, 9:33 PM                                            *
 * Asadbek Rajabboyev                                                         *
 ******************************************************************************/
package uz.asadbek.student.app.controllers;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import uz.asadbek.student.app.dto.TeacherDto;
import uz.asadbek.student.app.models.Teacher;
import uz.asadbek.student.app.service.TeacherService;
import uz.asadbek.student.app.util.ResponseError;
import uz.asadbek.student.app.util.UserNotCreatedExeption;
import uz.asadbek.student.app.util.UserNotFoundException;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;
@RestController
@RequestMapping("/api/teachers")
public class TeacherController {
	private final TeacherService teacherService;
	private final ModelMapper modelMapper;
	@Autowired
	public TeacherController(TeacherService teacherService, ModelMapper modelMapper) {
		this.teacherService = teacherService;
		this.modelMapper = modelMapper;
	}
	/******************List and one Teacher info***************************/
	@GetMapping()
	public List<TeacherDto> getAllTeacher(){
		return teacherService.getAllTeachers().stream().map(this::convertToTeacherDto).collect(Collectors.toList());
	}
	@GetMapping("/{id}")
	public TeacherDto getTeacher(@PathVariable("id") int id){
		Teacher teacher = teacherService.getOneTeacher(id);
		if (teacher == null) {
			throw new UserNotFoundException();
		}
		return convertToTeacherDto(teacher);
	}
	/******************List and one Teacher info***************************/




	/***********************************************Create Teacher**************************************************************************************/
	@PostMapping("/create")
	public ResponseEntity<String>createTeacher(@RequestBody @Valid TeacherDto teacherDto, BindingResult bindingResult) throws UserNotCreatedExeption {
		if (bindingResult.hasErrors()){
			StringBuilder stringBuilder= new StringBuilder();
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			for (FieldError fieldError: fieldErrors) {
				stringBuilder.append(fieldError.getField())
						.append("-")
						.append(fieldError.getDefaultMessage())
						.append(";");
			}
			throw new UserNotCreatedExeption(stringBuilder.toString());
		}
		teacherService.createTeacher(convertToTeacher(teacherDto));
		return new ResponseEntity<>("Muavvfaqiyatli qoshildi", HttpStatus.CREATED);
	}
	/***********************************************Create Teacher**************************************************************************************/





	/****************************************Update Teacher********************************************************************************************************/
	@PatchMapping("/{id}")
	public ResponseEntity<String> updateTeacher(@PathVariable("id") int id, @RequestBody @Valid TeacherDto teacherDto, BindingResult bindingResult){
		if (bindingResult.hasErrors()){
			StringBuilder stringBuilder= new StringBuilder();
			List<FieldError> fieldErrors = bindingResult.getFieldErrors();
			for (FieldError fieldError: fieldErrors) {
				stringBuilder.append(fieldError.getField())
						.append("-")
						.append(fieldError.getDefaultMessage())
						.append(";");
			}
			throw new UserNotCreatedExeption(stringBuilder.toString());
		}

		Teacher existingTeacher = teacherService.getOneTeacher(id);
		logAction("Teacher with ID " + id +" "+ existingTeacher.getName() +" "+ " updated.");

		Teacher updatedTeacher = convertToTeacher(teacherDto);
		updatedTeacher.setId(id);
		teacherService.updateTeacher(updatedTeacher);
		return new ResponseEntity<>("Muavvafaqiyatli yangilandi", HttpStatus.OK);
	}
	/****************************************Update Teacher********************************************************************************************************/






	/**********************************Delete Teacher by id*********************************************/
	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteTeacher(@PathVariable("id") int id) {
		Teacher existingTeacher = teacherService.getOneTeacher(id);
		if (existingTeacher == null) {
			throw new UserNotFoundException();
		}
		teacherService.deleteTeacher(id);
		logAction("Teacher with ID "+ id + " " + existingTeacher.getName() +" deleted");
		return new ResponseEntity<>("Muavvafaqiyatli o'chirildi", HttpStatus.OK);
	}
	/*********************************Delete Teacher by id**********************************************/




	/*****************Converts***********************************************************************************/
	private TeacherDto convertToTeacherDto(Teacher teacher) {
		return modelMapper.map(teacher, TeacherDto.class);
	}
	private Teacher convertToTeacher(TeacherDto teacherDto) {
		return modelMapper.map(teacherDto, Teacher.class);
	}
	/****************Converst************************************************************************************/





	private void logAction(String action) {
		String fileName = "history.txt";
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String timeStamp = dateFormat.format(new Date());

		try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName, true))) {
			writer.write(timeStamp + " - " + action);
			writer.newLine();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/***************************Exceptions*******************************************************/
	@ExceptionHandler
	private ResponseEntity<ResponseError> handlerExeptionCreated(UserNotCreatedExeption e) {
		ResponseError responseError = new ResponseError();
		responseError.setMessage(e.getMessage());
		responseError.setTimestamp(System.currentTimeMillis());
		return new ResponseEntity<>(responseError, HttpStatus.BAD_REQUEST);
	}
	@ExceptionHandler
	private ResponseEntity<ResponseError> handlerExeption(UserNotFoundException e) {
		ResponseError responseError = new ResponseError("Bunday IDga ega ustoz yoki markaz yo'q",System.currentTimeMillis());
		return new ResponseEntity<>(responseError, HttpStatus.NOT_FOUND); // 404 status code
	}
	/***************************Exceptions*******************************************************/

}
