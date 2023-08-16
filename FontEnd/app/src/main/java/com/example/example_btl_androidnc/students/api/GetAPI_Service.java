package com.example.example_btl_androidnc.students.api;

import com.example.example_btl_androidnc.students.model.Blog;
import com.example.example_btl_androidnc.students.model.ChangePass;
import com.example.example_btl_androidnc.students.model.Rank;
import com.example.example_btl_androidnc.students.model.RefreshTokenRequest;
import com.example.example_btl_androidnc.students.model.Schedule;
import com.example.example_btl_androidnc.students.model.UserCourse;
import com.example.example_btl_androidnc.students.model.Users;
import com.example.example_btl_androidnc.students.model.Course;

import java.util.List;

import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Part;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface GetAPI_Service {
    //hiển thị lớp học ở trang chủ
    @GET("/api/courses/users/schedules")
    Call<List<Course>> getCourse();
//    @GET("/api/courses/users/schedules")
//    Call<List<Course>> getCourse();
    @GET("/api/profile")
    Call<List<Users>> getUser();

    // đăng kí lớp học theo user đã đăng nhập
    @POST("/api/enrollCourse/{courseId}")
    Call<Void> enrollCourse(@Path("courseId") String courseId);

        @GET("/user/course/{id}")
    Call<List<UserCourse>> getCourseById(@Path("id") String id);


    // danh sách học viên của lớp học
    @GET("/api/course/{id}/users/role_user")
    Call<List<Users>> getUsersWithRoleUserInCourse(@Path("id") String courseId);


    // lấy danh sách sinh viên điểm danh
    @GET("/attendance/{scheduleId}")
    Call<List<Users>> getUserAndAttendanceBySchedule(@Path("scheduleId") String scheduleId);
    // Đăng kí tài khoản
    @POST("/api/auth/register")
    Call<Users> createAccount(@Body Users req);

    // đăng nhập
    @POST("/api/auth/login")
    Call<Users> login(@Body Users users);

    @POST("api/auth/refresh")
    Call<Users> refreshToken(@Body RefreshTokenRequest refreshTokenRequest);

    @GET("users/{username}")
    Call<Users> getUser(@Path("username") String username, @Header("Authorization") String token);
    //profile

    @GET("/api/profile")
    Call<Users> getUserProfile(@Header("Authorization") String token);

    // đổi mật khẩu
    @POST("/api/change-password")
    Call<ResponseBody> changePassword(@Body ChangePass changePass);

    // quên mật khẩu
    @POST("/api/forgot-password")
    Call<Void> processForgotPassword(@Query("email") String email);


    //cập nhật profile

    @POST("/api/update-profile")
    Call<ResponseBody> updateUser(@Body  Users users);
    //@Multipart
    @POST("/api/update-profile")
    Call<ResponseBody> updateAvatar(@Body  Users users);
//    Call<Users> updateAvatar(
//            @Header("Authorization") String token,
//           @Part MultipartBody.Part realPart
//    );

    // notification gửi token đến server
    @PUT("/notification/{userId}")
    Call<Void> updateTokenNotification(@Path("userId") String userId, @Query("tokenNotification") String tokenNotification);


    @GET("/courses/{id}/schedule")
    Call<List<Schedule>> getListScheduleByCourse(@Path("id") String courseId);

    @PUT("/api/update/schedules/{id}")
    Call<Void> updateSchedule(@Path("id") String id, @Body Schedule req);

    //tạo danh sách điểm danh
    @GET("/schedule/{scheduleId}")
    Call<ResponseBody> getUserOfCourseListByScheduleId(@Path("scheduleId") String scheduleId);


    @Multipart
    @POST("/api/update-profile")
    Call<ResponseBody> updateProfile(
            @Part("req") RequestBody req,
            @Part MultipartBody.Part image);


    //hiển thị tin tức
    @GET("/blog/{id}")
    Call<Blog> getBlog(@Path("id") int id);

    @GET("/admin/blog/list")
    Call<List<Blog>> getBlogs();

    // điểm danh
    @PUT("update-attendance/{schedule_id}")
    Call<Void> updateAttendance(@Path("schedule_id") String scheduleId, @Body List<Users> selectedStudents);

    // hiện thị user đi học và không điểm danh
    @GET("/course/{courseId}/user/{userId}")
    Call<List<Schedule>>getAttendanceInfoByCourseIdAndUserId(@Path("courseId") String courseId, @Path("userId") String userid);


    // cập nhật điêm
    @POST("/course/{courseId}/users/{userId}/grades")
    Call<Void> createGrades(@Path("courseId") String courseId,
                            @Path("userId") String userId,
                            @Body Rank req);


    //lấy điểm sinh viên
    @GET("/course/{courseId}/users/{userId}/grades")
    Call<Rank> getGradesByUserIdAndCourseId(@Path("courseId") String courseId, @Path("userId") String userId);
}
