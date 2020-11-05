/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.antoinelamoureux.cda_jsf_jpa.entities;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author admin
 */
@Entity
@Table(name = "news")
@NamedQueries({
    @NamedQuery(name = "News.findAll", query = "SELECT n FROM News n"),
    @NamedQuery(name = "News.findByIdNews", query = "SELECT n FROM News n WHERE n.idNews = :idNews"),
    @NamedQuery(name = "News.findByTitre", query = "SELECT n FROM News n WHERE n.titre = :titre"),
    @NamedQuery(name = "News.findByDatePub", query = "SELECT n FROM News n WHERE n.datePub = :datePub")})
public class News implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_news")
    private Integer idNews;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "titre")
    private String titre;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 16777215)
    @Column(name = "content")
    private String content;
    @Basic(optional = false)
    //@NotNull
    @Column(name = "date_pub", nullable = true, updatable = false, insertable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date datePub;
    @JoinColumn(name = "theme", referencedColumnName = "id_theme")
    @ManyToOne(optional = false)
    private Themes theme;
    @JoinTable(name = "news_tags", joinColumns = {
        @JoinColumn(name = "news", referencedColumnName = "id_news")}, inverseJoinColumns = {
        @JoinColumn(name = "tag", referencedColumnName = "id_tag")
    })
    @ManyToMany(fetch = FetchType.EAGER)
    private Collection<Tags> tagsCollection;

    public News() {
    }

    public News(Integer idNews) {
        this.idNews = idNews;
    }

    public News(Integer idNews, String titre, String content, Date datePub) {
        this.idNews = idNews;
        this.titre = titre;
        this.content = content;
        this.datePub = datePub;
    }

    public Integer getIdNews() {
        return idNews;
    }

    public void setIdNews(Integer idNews) {
        this.idNews = idNews;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Date getDatePub() {
        return datePub;
    }

    public void setDatePub(Date datePub) {
        this.datePub = datePub;
    }

    public Themes getTheme() {
        return theme;
    }

    public void setTheme(Themes theme) {
        this.theme = theme;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNews != null ? idNews.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof News)) {
            return false;
        }
        News other = (News) object;
        if ((this.idNews == null && other.idNews != null) || (this.idNews != null && !this.idNews.equals(other.idNews))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.antoinelamoureux.cda_jsf_jpa.entities.News[ idNews=" + idNews + " ]";
    }

    public Collection<Tags> getTagsCollection() {
        return tagsCollection;
    }

    public void setTagsCollection(Collection<Tags> tagsCollection) {
        this.tagsCollection = tagsCollection;
    }
    
}
