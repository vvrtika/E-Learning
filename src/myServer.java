import com.vmm.JHTTPServer;
import java.io.File;
import java.util.Properties;
import java.sql.*;
        
public class myServer extends JHTTPServer 
{
    Response res=null;
    myServer(int port) throws Exception
    {
        super(port);
    }
    @Override
    public Response serve(String uri,String method,Properties header,Properties parms,Properties files){
        if(uri.equals("/")){
            String ans =Math.random()+"";
            res = new Response (HTTP_OK, "text/plain", ans);
            return res;
        }
        else if(uri.equals("/one"))
        {
           res=new Response (HTTP_OK , "text/plain" , "Hello World");
            return res;
        }
        else if(uri.equals("/Login"))
        {
            String email= parms.getProperty("email");
            String pass=parms.getProperty("pass");
            try{
            ResultSet rs=DBLoader.executeQuery("select * from users where email='"+email+"' and password='"+pass+"'");
            if(rs.next()){
                String ans="success";
                res=new Response(HTTP_OK,"text/plain",ans);
                return res;
            }
            else{
                String ans="fail";
                res=new Response(HTTP_OK,"text/plain",ans);
                return res;
            }
            }
            catch (Exception ex){
                ex.printStackTrace();
            }
                
            }
         else if(uri.equals("/Signup"))
                {
                    String email= parms.getProperty("email");
                     String pass = parms.getProperty("pass");
                      String mobile= parms.getProperty("mobile");
                      String address=parms.getProperty("address");
                      //save file
                      
                      String photoname= saveFileOnServerWithRandomName(files, parms, "ph","src/uploads/");
                      try
                      {
                          ResultSet rs= DBLoader.executeQuery("select*from users where email='"+email+"'");
                          if(rs.next())
                          {
                              res= new Response( HTTP_OK,"text/plain", "exist");
                              return res;
                          }
                            else
                          {
                              rs.moveToInsertRow();
                              rs.updateString("email",email);
                              rs.updateString("password",pass);
                              rs.updateString("mobile",mobile);
                              rs.updateString("address", address);
                              rs.updateString("photo", "src/uploads/"+photoname);
                              rs.insertRow();
                              res=new Response (HTTP_OK, "text/plain","success");
                              return res;
                              }
                      }
                      catch(Exception ex){
                          ex.printStackTrace();
                          
                      }
                      
                
                }
         else if (uri.equals("/fetchcat"))
         {
             String ans="";
             try
             {
               ResultSet rs=DBLoader.executeQuery("select * from category");
               while(rs.next())
               {
                   String name= rs.getString("name");
                   String photo= rs.getString("photo");
                   
                   String row= name+"$"+photo;
                   
                   ans=ans+row+";;";
                   
               }
               
               res= new Response (HTTP_OK,"text/plain", ans);
               return res;
             }
             catch(Exception e){
                 e.printStackTrace();
             }
         }
         else if(uri.equals("/courses"))
         {
             String ans="";
             String category= parms.getProperty("category");
             try
             {
                 ResultSet rs=DBLoader.executeQuery("select * from courses where category ='"+category+"'");
                 while(rs.next())
                 {
                     int id=rs.getInt("id");
                     String name=rs.getString("name");
                     String photo=rs.getString("photo");
                     
                     String row= id+"$"+name+"$"+photo;
                     ans=ans+row+";;";
                     
                    
                 }
                  return new Response(HTTP_OK,"text/plain",ans);
             }   
             catch(Exception ex)
             {
                 ex.printStackTrace();
             }
         }
         
         else if( uri.equals("/fetchlectures"))
         {
             int course_id=Integer.parseInt(parms.getProperty("course_id"));
             String ans="";
             try
             {
                 ResultSet rs= DBLoader.executeQuery("select * from lectures where course_id="+course_id);
                 
                 while(rs.next())
                 {
                     int id= rs.getInt("id");
                     String name=rs.getString("name");
                     String photo= rs.getString("photo");
                     
                     String row= id+"$"+name+"$"+photo;
                     
                     ans=ans+row+";;";
                     
                 }
                 res= new Response (HTTP_OK,"text/plain",ans);
                 
                 return res;
             }
             catch(Exception ex)
             {
                ex.printStackTrace();
             }
         }
         else if(uri.equals("/fetchdetails"))
         {
             String ans="";
             int id=Integer.parseInt(parms.getProperty("id"));
                     try
                     {
                        ResultSet rs=DBLoader.executeQuery("select * from lectures where id="+id);
                        if(rs.next())
                        {
                         int id1 =rs.getInt("id");
                         String name= rs.getString("name");
                         String description = rs.getString("description");
                         String duration =rs.getString("duration");
                         String photo=rs.getString("photo");
                         int course_id= rs.getInt("course_id");
                         String trailer= rs.getString("trailer");
                         String video=rs.getString("video");
                         
                         ans=id1+"$"+name+"$"+description+"$"+duration+"$"+photo+"$"+course_id+"$"+trailer+"$"+video;
                         
                        }
                        res= new Response(HTTP_OK, "text/plain", ans);
                        
                       
                     }
                     catch(Exception ex)
                     {
                         ex.printStackTrace();
                     }
         }
         else if(uri.equals("/adminlogin"))
         {
             String username= parms.getProperty("username");
             String password= parms.getProperty("password");
             
             try
             {
                 ResultSet rs= DBLoader.executeQuery("select * from admin where username= '"+username+"' and password = '"+password+"' ");
                 if(rs.next())
                 {
                     res= new Response(HTTP_OK, "text/plain","success");
                     return res;
                 }
                 else
                 {
                     res= new Response(HTTP_OK, "text/plain","fail");
                     return res;
                 }
                 
             }
             catch(Exception ex)
             {
                 ex.printStackTrace();
             }
         }
         else if(uri.equals("/addcategory"))
         {
             String category= parms.getProperty("category");
             String photoname= saveFileOnServerWithRandomName(files, parms, "f1","src/uploads/");
             
             try
             {
               ResultSet rs= DBLoader.executeQuery("select * from category where name='"+category+"'");
               if(rs.next())
               {
                   res= new Response (HTTP_OK,"text/plain","exist");
                   return res;
               }
               else
               {
                   rs.moveToInsertRow();
                   rs.updateString("name",category);
                   rs.updateString("photo","src/uploads/"+photoname);
                   rs.insertRow();
                   
                   res= new Response (HTTP_OK,"text/plain","success");
                   return res;
               }
             }
             catch(Exception ex)
             {
                 ex.printStackTrace();
             }
         }
         else if(uri.equals("/fetchCat"))
         {
             String ans="";
            try
            {
                ResultSet rs= DBLoader.executeQuery("select * from category");
                while(rs.next())
                {
                    String name= rs.getString("name");
                    String photo= rs.getString("photo");
                    
                    String row=name+"$"+photo;
                    
                    ans= ans+row+";;";
                }
                res = new Response(HTTP_OK,"text/plain",ans);
                return res;
            } 
            catch (Exception ex)
            {
                ex.printStackTrace();
            }
         
         }
         else if(uri.equals("/deletecat"))
         {
             String name = parms.getProperty("name");
             
             try 
             {
                ResultSet rs = DBLoader.executeQuery("select * from category where name='"+name+"'");
                
                if(rs.next())
                {
                    rs.deleteRow();
                    
                    res = new Response(HTTP_OK, "text/plain", "success");
                     return res;
                }
                else
                {
                      res = new Response(HTTP_OK, "text/plain", "fail");
                     return res;
                }
             } 
             catch (Exception e) 
             {
              e.printStackTrace();
             }
         }
         else if(uri.equals("/fetchCategories"))
         {
             String ans="";
             try
             {
                ResultSet rs= DBLoader.executeQuery("select * from category");
                
                while(rs.next())
                {
                    String name= rs.getString("name");
                    ans=ans+name+";;";  
                }
                res= new Response(HTTP_OK, "text/plain",ans);
                return res;
                
             }
             catch(Exception e)
             {
                 e.printStackTrace();
             }
         }   
          else if(uri.equals("/addcourse"))
         {
 
             try 
             {
                String coursename = parms.getProperty("coursename");
                String coursedescription = parms.getProperty("coursedescription");
                String category = parms.getProperty("category");
             
                String photoname = saveFileOnServerWithRandomName(files, parms, "ph", "src/uploads/");    // for removing forward slash
       
               
         ResultSet rs = DBLoader.executeQuery("select * from courses");
        
        rs.moveToInsertRow();
        
        rs.updateString("name", coursename);
        rs.updateString("description", coursedescription);
        rs.updateString("photo", "src/uploads/"+photoname);
        rs.updateString("category", category);
        
        rs.insertRow();
        
        res = new Response(HTTP_OK, "text/plain", "success");
        return res;
        
             }
             catch (Exception e) 
             {
              e.printStackTrace();
             }
         
         
         }
           else if(uri.equals("/fetchcourses"))
         {
             String ans = "";
             
             String Category = parms.getProperty("Category");
             
             try
             {
                 ResultSet rs = DBLoader.executeQuery("select * from courses where category = '"+Category+"'");
                 
                 while(rs.next())
                 {
                     int id = rs.getInt("id");
                     String name = rs.getString("name");
                     String desc = rs.getString("description");
                     String photo = rs.getString("photo");
                     
                     String row = id+"$"+name+"$"+desc+"$"+photo;
                     
                     ans = ans+row+";;";
                     
                 }
                 
                 res = new Response(HTTP_OK, "text/plain", ans);
                 return res;
             }
                          catch(Exception ex)
             {
                 ex.printStackTrace();
             }
             
         }
            else if(uri.equals("/deletecourse"))
             
         {
             int id = Integer.parseInt(parms.getProperty("id"));
             
             try {
                 
                 ResultSet rs = DBLoader.executeQuery("select * from courses where id="+id);
                 
                 if(rs.next())
                 {
                     rs.deleteRow();
                     
                     String ans = "success";
                     
                     res = new Response(HTTP_OK, "text/plain", ans);
                     return res;
                 }
                 
             } 
             catch (Exception e)
             {
                 e.printStackTrace();
             }
         }
             else if(uri.equals("/loadcourses"))
         {
             String ans="";
             
             try
             {
                 ResultSet rs = DBLoader.executeQuery("select * from courses");
                 
                 while(rs.next())
                 {
                     int id = rs.getInt("id");
                     String name = rs.getString("name");
                     
                     String row = id+"$"+name;
                     
                     ans = ans+row+";;";
                 }
                 
               res = new Response(HTTP_OK, "text/plain", ans);
               return res;
             }
             catch(Exception ex)
             {
                 ex.printStackTrace();
             }
         }
              else if(uri.equals("/addlecture"))
         {
             String name = parms.getProperty("name");
              String desc = parms.getProperty("description");
               String duration = parms.getProperty("duration");
             String photoname = saveFileOnServerWithRandomName(files, parms, "ph", "src/uploads/");
             int course_id = Integer.parseInt(parms.getProperty("course_id"));
             String trailer = parms.getProperty("trailer");
             String videoname = saveFileOnServerWithRandomName(files, parms, "lecture", "src/Lectures/");
             
             try
             {
                  ResultSet rs = DBLoader.executeQuery("select * from Lectures");
        
        rs.moveToInsertRow();
        
        rs.updateString("name", name);
         rs.updateString("description", desc);
          rs.updateString("duration", duration);
           rs.updateString("photo", "src/uploads/"+photoname);
           rs.updateInt("course_id", course_id);
           rs.updateString("trailer", trailer);
            rs.updateString("video", "src/Lectures/"+videoname);
            rs.insertRow();
            
            res = new Response(HTTP_OK, "text/plain", "success");
            return res;
           
             }
             
             catch (Exception e) {
                 e.printStackTrace();
             }
 
         }
              else if(uri.equals("/fetchlectures1"))
         {
             String ans = "";
             int course_id = Integer.parseInt(parms.getProperty("course_id"));
            try
            {
             ResultSet rs  = DBLoader.executeQuery("select * from Lectures where course_id="+course_id);
             
             while(rs.next())
             {
               int id  = rs.getInt("id");
               String name = rs.getString("name");
               String description = rs.getString("description");
               String photo = rs.getString("photo");
               
               String row = id+"$"+name+"$"+description+"$"+photo;
               
               ans = ans+row+";;";
               
             }
             
             res = new Response(HTTP_OK, "text/plain", ans);
             return res;
            }
            catch(Exception ex)
            {
                ex.printStackTrace();
            }
         }
         
         else if(uri.equals("/deletelecture"))
         {
             int id = Integer.parseInt(parms.getProperty("id"));
             try 
             {
             ResultSet rs = DBLoader.executeQuery("select * from Lectures where id="+id);
             
             if(rs.next())
             {
                 rs.deleteRow();
                 res = new Response(HTTP_OK, "text/plain", "success");
                 return res;
             }
             else
             {
                   res = new Response(HTTP_OK, "text/plain", "fail");
                 return res;
             }
             
             
             }
             catch (Exception e)
             {
                e.printStackTrace();
             }
         }
         
        else
         {
             res=new Response (HTTP_OK , "text/plain" , "invalid uri");
            return res;
        }
       
        return res;
        
    } 
    
    
    //public static void main(String[] args) {
     //   try
      //  {
       // myServer obj= new myServer(9000);
       // Thread.sleep(1000000000);
       // }
       // catch(Exception ex)
       // {
         //   ex.printStackTrace();
       // }
   // }
    
    
}
