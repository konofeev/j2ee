package konofeev.service;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.LogManager;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import konofeev.entities.Content;
import konofeev.log.Loggable;

/**
 * Сервис работы с контентом
 */
@Named("content")
@RequestScoped
@Loggable
public class ContentService
{
    @PersistenceContext(unitName="j2ee")
    EntityManager entityManager;

    /**
     * Получить список контента
     *
     * @return Список контента
     */
    public List<Content> getContentList()
    {
        return entityManager.createNamedQuery
        (
            "getAllContent",
            Content.class
        ).getResultList();
    }

    public Content save(Content content)
    {
        return null;
    }
}
