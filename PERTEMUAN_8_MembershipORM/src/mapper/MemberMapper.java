/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package mapper;
import java.util.List;
import model.Member;
import model.JenisMember;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Results;
import org.apache.ibatis.annotations.Result;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.apache.ibatis.annotations.One;
import java.util.List;

/**
 *
 * @author Daffa
 */

public interface MemberMapper {

    @Insert("Insert into member (id, nama, jenis_member_id) values(#{id}, #{nama}, #{jenisMemberId})")
    public Integer insert(Member member);

    @Update("update member set nama = #{nama}, jenis_member_id = #{jenisMemberId} where id = #{id}")
    public Integer update(Member member);

    @Delete("delete from member where id = #{id}")
    public Integer delete(Member member);

    @Select("SELECT * FROM member")
    @Results(value = {
        @Result(property = "id", column = "id"),
        @Result(property = "nama", column = "nama"),
        @Result(property = "jenisMemberId", column = "jenis_member_id"),
        @Result(property = "jenisMember", column = "jenis_member_id", 
                javaType = JenisMember.class, one = @One(select = "getJenisMember"))
    })
    List<Member> findAll();

    @Select("SELECT * FROM jenis_member WHERE id = #{id}")
    @Results(value = {
        @Result(property = "id", column = "id"),
        @Result(property = "nama", column = "nama")
    })
    JenisMember getJenisMember(String jenisMemberId);
}

