package konofeev.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.enterprise.context.RequestScoped;
import konofeev.log.Loggable;

/**
 * Контент
 */
@RequestScoped
@Loggable
@Entity
@NamedQueries(
{
    @NamedQuery
    (
        name  = "getAllContent",
        query = "SELECT c FROM Content c"
    )
})
public class Content
{

    private static final long serialVersionUID = 1L;
    
    /**
     * Идентификатор
     */
    @Id
    @GeneratedValue
    private Integer id;
    public Integer getId()
    {
        return id;
    }
    public void setId(Integer id)
    {
        this.id = id;
    }
    
    /**
     * Наименование
     */
    @NotNull
    private String name;
    public String getName()
    {
        return name;
    }
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * Содержимое
     */
    @Size(min = 10, max = 2000)
    private String content;
    public String getContent()
    {
        return content;
    }
    public void setContent(String content)
    {
        this.content = content;
    }

    /**
     * Конструктор по умолчанию
     */
    public Content()
    {
    }

    /**
     * Конструктор
     */
    public Content(Integer id, String name, String content)
    {
        this.id      = id;
        this.name    = name;
        this.content = content;
    }

    public Content(String name, String content)
    {
        this.name    = name;
        this.content = content;
    }

    /**
     * Формирование хэш-кода
     */
    @Override
    public int hashCode()
    {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    /**
     * Сравнение объектов
     */
    @Override
    public boolean equals(Object object)
    {
        if (!(object instanceof Content))
        {
            return false;
        }

        Content other = (Content) object;

        if ((this.id == null && other.id != null) ||
            (this.id != null && !this.id.equals(other.id)))
        {
            return false;
        }

        return true;
    }

    /**
     * Преобразование к строке
     */
    @Override
    public String toString()
    {
        return name;
    }
}
