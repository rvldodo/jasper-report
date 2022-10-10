package com.dodo;

import com.dodo.entities.Student;
import com.dodo.service.JasperReportService;

import javax.inject.Inject;
import javax.transaction.Transactional;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.File;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

@Path("student")
@Produces("application/json")
@Consumes("application/json")
public class StudentResource {

    private static final Logger LOGGER = Logger.getLogger(StudentResource.class.getName());

    @Inject
    JasperReportService jasperReportService;

    @GET
    public Response getAll() throws Exception {
        File dir = new File("outputPDF");
        String uuidToken = UUID.randomUUID().toString();
        String filename = "report" + "_" + uuidToken + ".pdf";
        String outputFile = dir.getPath() + "/" + filename;
        Map<String, Object> parameter = new HashMap<>();
        List<Student> students = Student.findAll().list();
        try{
            if(!dir.exists()) {
                dir.mkdir();
                if(dir.isDirectory()) {
                    String jasperReportPath = "src/main/resources/student.jrxml";
                    jasperReportService.generatedPdfReport(jasperReportPath, outputFile, parameter);
                }
            }

            return Response.status(201, "PDF file successfully created").build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).build();
        }
    }

    @GET
    @Path("{uuid}")
    public Student getByUuid(@PathParam("uuid") UUID uuid) {
        return Student.findById(uuid);
    }

    @POST
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Student createStudent(Student student) {
        student.persist();

        return student;
    }

    @PUT
    @Path("{uuid}")
    @Transactional
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    public Student updateStudent(@PathParam("uuid") UUID uuid, Student newStudent) {
        Student oldStudent = Student.findById(uuid);

        oldStudent.setFirstName(newStudent.getFirstName());
        oldStudent.setLastName(newStudent.getLastName());
        oldStudent.setEmail(newStudent.getEmail());
        oldStudent.setOccupation(newStudent.getOccupation());
        oldStudent.setDob(newStudent.getDob());

        return oldStudent;
    }

    @DELETE
    @Path("{uuid}")
    @Transactional
    public Response deleteStudent(@PathParam("uuid") UUID uuid) {
        Student student = Student.findById(uuid);

        Student.deleteById(uuid);

        return Response.ok(student).build();
    }
}
