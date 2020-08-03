package com.ssafy.devlog.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.devlog.dto.Post;
import com.ssafy.devlog.mapper.PostMapper;

@Service
public class PostServiceImpl implements PostService{

	@Autowired
	private PostMapper postMapper;
	
	@Override
	public int selectPostCntByFeed(int seq_user, int disclosure, List<String> tag) {
		return postMapper.selectPostCntByFeed(seq_user, disclosure, tag);
	}
	
	@Override
	public List<Post> selectPostByFeed(int seq_user,int disclosure,int offset,int limit,List<String> tag){
		return postMapper.selectPostByFeed(seq_user, disclosure, offset, limit, tag);
	}
	
	@Override
	public int selectPostCntByBlog(int seq_user, int disclosure, List<String> tag) {
		return postMapper.selectPostCntByBlog(seq_user, disclosure, tag);
	}
	
	@Override
	public List<Post> selectPostByBlog(int seq_user,int disclosure,int offset,int limit,List<String> tag){
		return postMapper.selectPostByBlog(seq_user, disclosure, offset, limit, tag);
	}
	
	@Override
	public Post selectPost(int seq){
		return postMapper.selectPost(seq);
	}
	
	@Override
	public int insertPost(Post post){
		return postMapper.insertPost(post);
	}
	
	@Override
	public int insertPostBasic(Post post){
		return postMapper.insertPostBasic(post);
	}
	
	@Override
	public int updatePost(Post post){
		return postMapper.updatePost(post);
	}
	
	@Override
	public int updatePostBasic(Post post) {
		return postMapper.updatePostBasic(post);
	}
	
	@Override
	public int deletePost(int seq){
		return postMapper.deletePost(seq);
	}

}