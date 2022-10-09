package uz.example.less66_retrofitrequest_java.network.service;

import java.util.ArrayList;
import retrofit2.Call;
import retrofit2.http.*;
import uz.example.less66_retrofitrequest_java.model.BaseResponse;
import uz.example.less66_retrofitrequest_java.model.EmplResp;
import uz.example.less66_retrofitrequest_java.model.Employee;

public interface EmployeeService {
    @Headers(
        "Content-type:application/json"
    )

    @GET("employees")
     Call<BaseResponse<ArrayList<EmplResp>>> listEmployee();

    @GET("employee/{id}")
    Call<BaseResponse<EmplResp>> singleEmployee(@Path("id") int id);

    @POST("create")
    Call<BaseResponse<EmplResp>> createEmployee(@Body Employee employee );

    @PUT("update/{id}")
    Call<BaseResponse<EmplResp>> updateEmployee(@Path("id") int id, @Body Employee employee );

    @DELETE("delete/{id}")
    Call<BaseResponse<String>> deleteEmployee(@Path("id") int id);

    /*@Multipart
    @POST("v1/images/upload")
    fun uploadImage(@Part file: MultipartBody.Part, @Part("sub_id") sub_id: RequestBody): Call<ResponseBody>*/
}
