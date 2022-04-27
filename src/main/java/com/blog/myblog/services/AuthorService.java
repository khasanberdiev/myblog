package com.blog.myblog.services;

import java.util.List;
// import java.util.Optional;
import java.util.Optional;

import com.blog.myblog.repositories.AuthorRespository;
import com.blog.myblog.exceptions.NotFoundException;
import com.blog.myblog.models.Author;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class AuthorService {
    @Autowired
    private AuthorRespository authorRepository;

    public Page<Author> authorPageableList(int pageNumber, int pageSize, String sortField, String sortDirection)
    {
        Sort sort =sortDirection.equalsIgnoreCase(Sort.Direction.ASC.name())?Sort.by(sortField).ascending():Sort.by(sortField).descending();
        Pageable pageable=PageRequest.of(pageNumber-1, pageSize, sort);
        return authorRepository.findAll(pageable);
    }

    public List<Author> authorList(){
        return (List<Author>) authorRepository.findAll();
        }

    public void save(Author author){
        authorRepository.save(author);
    }

    public Author findById(Long id){
        Optional <Author> authorFindById=authorRepository.findById(id);
        if(!authorFindById.isPresent()){
            throw new NotFoundException("Author not found");
        }

        return authorFindById.get();
    }

    public void deleteById(Long id){
        authorRepository.deleteById(id);
    }
    
    
}
