package mymediaMain.services;

import lombok.extern.slf4j.Slf4j;
import mymediaMain.config.SessionManager;
import org.mymedia.database.dao.UserDataBase;
import org.mymedia.database.entities.User;

@Slf4j
public class StatusService {

    public static final UserDataBase USER_DATA_BASE = UserDataBase.getDataBase();

    User template1;
    User template2;
    User template3;

    StatusService(){

        template1 = new User("kiss", "kiss", "kiss", "kiss");
        template2 = new User("111", "111", "111", "111");
        template3 = new User("111", "111", "111", "111");
    }

    public void logoutAllUser(){
        SessionManager.getInstance().clear();
    }

    public void registrateTemplate1(){
        Connector connector = new Connector();
        connector.userConnection();
        User template = new User("kiss", "kiss", "kiss", "kiss");
        try{
            USER_DATA_BASE.save(new User("kiss", "kiss", "kiss", "kiss"));
        } catch(Exception ex){
            log.error(ex +  "");
        }
    }

    public void registrateTemplate2(){
        Connector connector = new Connector();
        connector.userConnection();
        User template = new User("asd", "asd", "asd", "asd");
        try{
            USER_DATA_BASE.save(template);
        } catch(Exception ex){
            log.error(ex +  "");
        }
    }

    public void registrateTemplate3(){
        Connector connector = new Connector();
        connector.userConnection();
        try{
            USER_DATA_BASE.save(template);
        } catch(Exception ex){
            log.error(ex +  "");
        }
    }

    public void deleteTemplate1(){
        Connector connector = new Connector();
        connector.userConnection();
        try{
            USER_DATA_BASE.delete(new User("kiss", "kiss", "kiss", "kiss"));
        } catch (Exception ex){
            log.error(ex + "");
        }
    }

    public void deleteTemplate2(){
        Connector connector = new Connector();
        connector.userConnection();
        try{
            USER_DATA_BASE.delete(new User("asd", "asd", "asd", "asd"));
        } catch (Exception ex){
            log.error(ex + "");
        }
    }
    public void deleteTemplate3(){
        Connector connector = new Connector();
        connector.userConnection();
        try{
            USER_DATA_BASE.delete(new User("111", "111", "111", "111"));
        } catch (Exception ex){
            log.error(ex + "");
        }
    }
}
