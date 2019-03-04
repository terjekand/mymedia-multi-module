package mymediaMain.services;

import lombok.extern.slf4j.Slf4j;
import mymediaMain.config.SessionManager;
import database.dao.UserDataBase;
import database.entities.User;

@Slf4j
public class StatusService {

    public static final UserDataBase USER_DATA_BASE = UserDataBase.getDataBase();

    User template1;
    User template2;
    User template3;

    public StatusService(){

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
        try{
            USER_DATA_BASE.save(template1);
        } catch(Exception ex){
            log.error(ex +  "");
        }
    }

    public void registrateTemplate2(){
        Connector connector = new Connector();
        connector.userConnection();
        try{
            USER_DATA_BASE.save(template2);
        } catch(Exception ex){
            log.error(ex +  "");
        }
    }

    public void registrateTemplate3(){
        Connector connector = new Connector();
        connector.userConnection();
        try{
            USER_DATA_BASE.save(template3);
        } catch(Exception ex){
            log.error(ex +  "");
        }
    }

    public void deleteTemplate1(){
        Connector connector = new Connector();
        connector.userConnection();
        try{
            USER_DATA_BASE.delete(template1);
        } catch (Exception ex){
            log.error(ex + "");
        }
    }

    public void deleteTemplate2(){
        Connector connector = new Connector();
        connector.userConnection();
        try{
            USER_DATA_BASE.delete(template2);
        } catch (Exception ex){
            log.error(ex + "");
        }
    }
    public void deleteTemplate3(){
        Connector connector = new Connector();
        connector.userConnection();
        try{
            USER_DATA_BASE.delete(template3);
        } catch (Exception ex){
            log.error(ex + "");
        }
    }
}
