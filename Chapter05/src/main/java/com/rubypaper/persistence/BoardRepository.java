package com.rubypaper.persistence;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.rubypaper.domain.Board;

public interface BoardRepository extends CrudRepository<Board, Long> {
    List<Board> findByTitle(String searchKeyword);
    List<Board> findByContentContaining(String searchKeyword);
    List<Board> findByTitleContainingOrContentContaining(String title, String content); // 변수 두 개가 필요
    List<Board> findByTitleContainingOrderBySeqDesc(String searchKeyword);
    Page<Board> findByTitleContaining(String searchKeyword, Pageable paging);
    
//    @Query("SELECT b From Board b " + "WHERE b.title like %:searchKeyword% "+"ORDER BY b.seq DESC")
//    List<Board> queryAnnotationTest1(@Param("searchKeyword") String searchKeyword);
//    
//    @Query("SELECT b.seq, b.title, b.writer, b.createDate " + "From Board b " + "WHERE b.title like %?1% "+"ORDER BY b.seq DESC")
//    List<Object[]> queryAnnotationTest2(@Param("searchKeyword") String searchKeyword);
//    
//    @Query(value="select seq, title, writer, create_date " + "from board where title like '%'||?1||'%' " + "order by seq desc", nativeQuery = true)
//    List<Object[]> queryAnnotationTest3(String searchKeyword);
//    
//    @Query("SELECT b FROM Board b ORDER BY b.seq DESC")
//    List<Board> queryAnnotationTest4(Pageable paging);
}
