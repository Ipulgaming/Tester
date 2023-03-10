package pojo;
// Generated Oct 9, 2022 1:40:24 PM by Hibernate Tools 4.3.1

import dao.DAOLib;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import util.inpFilter;




/**
 * Lib generated by hbm2java
 */
@ManagedBean
public class Lib  implements java.io.Serializable {


     private Integer id;
     private String img;
     private String judul;
     private String penulis;
     private String genre;
     private String link;
     String gLink = "https://drive.google.com/uc?export=download&id=";

    public List<Lib> getAllRecords() {
        DAOLib lib = new DAOLib();
        List<Lib> listLib = lib.rtvBook();
        return listLib;
    }
    
    public String goBack(){
        FacesContext.getCurrentInstance().addMessage(
		null,
		new FacesMessage(FacesMessage.SEVERITY_WARN,
			"You're Cancelling Editing Book ",
			judul));
       img = "";
       judul = "";
       penulis = "";
       genre = "";
       link = ""; 
       return "dash";
    }
    
    public String getById(int ids) {
        id = ids;
        String idLib = Integer.toString(ids);
        DAOLib lib = new DAOLib();
        List<Lib> listLib = lib.getbyID(idLib);
        try {
            if (listLib != null) {
                img = "https://drive.google.com/file/d/"+listLib.get(0).img+"/view?usp=sharing";
                judul = listLib.get(0).judul;
                penulis = listLib.get(0).penulis;
                genre = listLib.get(0).genre;
                link = "https://drive.google.com/file/d/"+listLib.get(0).link+"/view?usp=sharing";
                return "edit";
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return "dash";
    }
    
    public String saveLib() {
        inpFilter fd = new inpFilter();
        String validation = fd.validateAddB(img, judul, penulis, link, genre);
            if (!validation.isEmpty()) {
                FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"ERROR WHILE ADDING NEW BOOK DUE TO:",
							validation));
            img = "";
            judul = "";
            penulis = "";
            genre = "";
            link = "";
            return "dash"; 
            } else {
            String ilink = img.replaceAll("https://drive\\.google\\.com/file/d/(.*?)/.*?\\?usp=sharing", "$1");
            String blink = link.replaceAll("https://drive\\.google\\.com/file/d/(.*?)/.*?\\?usp=sharing", "$1");
            img = ilink; link = blink;
            DAOLib lib = new DAOLib();
            lib.addBook(this);
                FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"SUCCESS ADDING NEW BOOK :",
							judul));
            img = "";
            judul = "";
            penulis = "";
            genre = "";
            link = "";
            }
        return "dash";   
    }
    
    public String editLib() {
        inpFilter fd = new inpFilter();
        String validation = fd.validateEditB(img, judul, penulis, link, genre);
        if (!validation.isEmpty()) {
            FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_ERROR,
							"ERROR WHILE EDITING BOOK : "+judul,
							validation));
            img = "";
            judul = "";
            penulis = "";
            genre = "";
            link = "";
            return "dash";
        } else {
            String ilink = img.replaceAll("https://drive\\.google\\.com/file/d/(.*?)/.*?\\?usp=sharing", "$1");
            String blink = link.replaceAll("https://drive\\.google\\.com/file/d/(.*?)/.*?\\?usp=sharing", "$1");
            img = ilink; link = blink;    
            DAOLib lib = new DAOLib();
            lib.editBook(this);
                FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"SUCCESS EDITING BOOK :",
							judul));
            img = "";
            judul = "";
            penulis = "";
            genre = "";
            link = "";
        }      
        return "dash";
    }
    
    public String deleteLib(int id) {
        DAOLib lib = new DAOLib();;
        lib.deleteBook(id);
        FacesContext.getCurrentInstance().addMessage(
					null,
					new FacesMessage(FacesMessage.SEVERITY_INFO,
							"SUCCESSFULLY DELETE BOOK : "+judul,
                                                "(Writer: "+penulis+"|| Genre: "+genre));
        img = "";
        judul = "";
        penulis = "";
        genre = "";
        link = "";
        return "dash";
    }
     
    public Lib() {
    }

    public Lib(String img, String judul, String penulis, String genre, String link) {
       this.img = img;
       this.judul = judul;
       this.penulis = penulis;
       this.genre = genre;
       this.link = link;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getImg() {
        return this.img;
    }
    
    public void setImg(String img) {
        this.img = img;
    }
    public String getJudul() {
        return this.judul;
    }
    
    public void setJudul(String judul) {
        this.judul = judul;
    }
    public String getPenulis() {
        return this.penulis;
    }
    
    public void setPenulis(String penulis) {
        this.penulis = penulis;
    }
    public String getGenre() {
        return this.genre;
    }
    
    public void setGenre(String genre) {
        this.genre = genre;
    }
    public String getLink() {
        return this.link;
    }
    
    public void setLink(String link) {
        this.link = link;
    }
    
    public String getgLink() {
        return this.gLink;
    }
    
    public void setgLink(String gLink) {
        this.gLink = gLink;
    }




}


