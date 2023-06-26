package com.example.demo.Upload_File.Single;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.http.HttpRequest;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Controller
public class FileController {

    @GetMapping("/fileUpload")
    public String view(){
        return "/upload-File/single-file";
    }

//////////////////Lấy ảnh có sẵn trong thư mục dự án
//    @PostMapping("/upload-file")
//    public String hienthi(@RequestParam("fileIMG")MultipartFile file, @RequestParam("name")String name,
//                          Model md, HttpServletRequest request){
//        if(file.isEmpty()){
//            return "";
//        }
//
//        //Lấy đường dẫn đến file chứa ảnh
//        String filePath = request.getContextPath() + "img";
//
//       String filePath1 = request.getServletContext().getRealPath("") + "img";
//
//        //Lấy tên ảnh được chọn
//        String tenAnh = file.getOriginalFilename();
//
//        String duongDanTong = filePath + "/" + tenAnh;
//
//        md.addAttribute("name",name);
//        md.addAttribute("imgPATH",duongDanTong);
//
//
//        System.out.println("ĐƯỜNG DẪN CỦA ẢNH :"+ duongDanTong);
//        System.out.println("ĐƯỜNG DẪN CỦA ẢNH LỖI :"+ filePath1+"\\"+tenAnh);
//
//        return "/upload-File/view-single";
//    }


    ////////Lấy ảnh từ mọi nơi trên máy tính và cho vào file dự án
    @PostMapping("/upload-file")
    public String hienthi(@RequestParam("fileIMG")MultipartFile file, @RequestParam("name")String name,
                          Model md, HttpServletRequest request)  {
        if(file.isEmpty()){
            return "";
        }
        //Lấy tên ảnh được chọn
        String tenAnh = file.getOriginalFilename();

        try{
        //Tạo đường dẫn đến folder chứa ảnh , nếu không tồn tại folder sẽ tự tạo mới 1 folder
        //Khởi tạo vị trí cho thư mục
        String uploadDir =request.getServletContext().getRealPath("") + "folderIMG";
        //Có thể tạo 1 folder con bằng cách thêm / , ví đụ folderIMG/abcde

        //Gọi tới đường dẫn
        Path uploadPath = Paths.get(uploadDir);

        //Kiểm tra tồn tại
        if(!Files.exists(uploadPath)){
            Files.createDirectories(uploadPath);
            System.out.println("tạo thư mục thành công");
            System.out.println("duong dan la ; "+ uploadDir);
        }


        //Gọi tới đường dẫn của ảnh trong folder của dự án
        Path duongDanAnh = uploadPath.resolve(tenAnh);

        //Copy img được chọn vào folder dự án
        Files.copy(file.getInputStream(),duongDanAnh, StandardCopyOption.REPLACE_EXISTING);

        // -->   file.getInputStream() : đọc dữ liệu từ tệp tin(MultipartFile) được tải lên
        // -->   duongDanAnh : vị trí của ảnh trong folder
        // -->  StandardCopyOption : Copy dữ liệu từ tệp được tải lên vào folder
            // các kiểu copy file
            // + REPLACE_EXISTING : copy , nếu đã tồn tại sẽ ghi đè lên cái cũ
            // + COPY_ATTRIBUTES : copy, nếu đã tồn tại thì sẽ có 2 ảnh giống nhau
            // + ATOMIC_MOVE : di chuyển ảnh từ thư mục gốc vào folder


       md.addAttribute("name",name);
       md.addAttribute("imgPATH","/folderIMG/"+tenAnh);

       return "/upload-File/view-single";
        }
        catch (IOException e){
            e.printStackTrace();
            return "";
        }
    }



}
