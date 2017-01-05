package hw.hw9;

public class StudentCourseMain {
   @SuppressWarnings("unused")
   public static void main(String[] args) {
	   CourseRegistrationModel model = new CourseRegistrationModel();

	   SaveView                sav = new SaveView();
	   FileController           fc = new FileController(model,sav);

	   CourseView               cv = new CourseView();
	   CourseViewController     cc = new CourseViewController(model, cv);

	   StudentView              sv = new StudentView();
	   StudentViewController    sc = new StudentViewController(model,sv);

	   EnrollmentView           ev = new EnrollmentView();
	   EnrollmentController     ec = new EnrollmentController(model, ev, sv, cv);

   }
}