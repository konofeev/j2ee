package konofeev.entities;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 * Аутентификация
 * Временное элементарное решение
 * TODO: данные хранить в БД, пароль шифровать и выполнять проверку хеш-функцией
 */
@ManagedBean (name="login")
@SessionScoped
public class Login
{
    /**
     * Логин пользователя
     */
    private String login;
    
    /**
     * Пароль
     */
    private String password;

    public String getLogin() 
    {
        return login;
    }

    public void setLogin(String login) 
    {
        this.login = login;
	}

	public String getPassword() 
    {
	    return password;
	}

	public void setPassword(String password) 
    {
	    this.password = password;
	}
 
    /**
     * Метод элементарной авторизации.
     * Выполняется проверка имени и пароля пользователя. 
     * Результат проверки - наименование страницы перехода
     */
    public String checkLogin()
    {
        if (login.equalsIgnoreCase("user") && 
            password.equalsIgnoreCase("password"))
        {
            return "login-done?faces-redirect=true";
        } 
        else 
        {
            return "login-failed?faces-redirect=true";
        }
    }
}
