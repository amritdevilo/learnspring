package com.prateek.learnspring.controller;


import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.prateek.learnspring.dao.MessageDao;
import com.prateek.learnspring.dao.SongsDao;
import com.prateek.learnspring.dao.UserDao;
import com.prateek.learnspring.model.AddSongResponse;
import com.prateek.learnspring.model.ClientResponse;
import com.prateek.learnspring.model.Message;
import com.prateek.learnspring.model.ServiceResponse;
import com.prateek.learnspring.model.Song;
import com.prateek.learnspring.model.SongAndRating;
import com.prateek.learnspring.model.UserAutocompleteResponse;
import com.prateek.learnspring.model.UserInfo;
import com.prateek.learnspring.model.UserMessage;
import com.prateek.learnspring.model.UserMessageResponse;
import com.prateek.learnspring.model.UserSearch;

@Controller
public class ApiController {

	@Autowired
	private SongsDao songsDao;
	
	@Autowired
	private MessageDao messageDao;
	
	@Autowired
	private UserDao userDao;
	
	@RequestMapping(value="/api/client", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ClientResponse> getCurrentUserInfo(HttpServletRequest request) {
		UsernamePasswordAuthenticationToken userToken = (UsernamePasswordAuthenticationToken)request.getUserPrincipal();
		if (userToken == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value())
					.body(new ClientResponse(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase(), null));
		}
		((UserInfo)(userToken.getPrincipal())).setFlashMessage("New Message "+ System.currentTimeMillis());
		UserInfo userInfo = (UserInfo)userToken.getPrincipal();
		return ResponseEntity.status(HttpStatus.OK)
				.body(new ClientResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), userInfo));
	}
	
	@RequestMapping(value="/api/song/add", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<AddSongResponse> addSong(HttpServletRequest request, @RequestBody Song song) {
		UsernamePasswordAuthenticationToken userToken = (UsernamePasswordAuthenticationToken)request.getUserPrincipal();
		if (userToken == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value())
					.body(new AddSongResponse(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase()));
		}
		
		String userId = ((UserInfo)(userToken.getPrincipal())).getId();
		try {
			System.out.println(song.getName() + " " + song.getLink());
			song.setUserId(userId);
			Song res = songsDao.addSong(song);
			HttpStatus status = HttpStatus.OK;
			if (null == res) {
				status = HttpStatus.NOT_MODIFIED;
			}
			return ResponseEntity.status(status.value())
					.body(new AddSongResponse(status.value(), status.getReasonPhrase(), res));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.body(new AddSongResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
		}
	}
	
	@RequestMapping(value="/api/song/ratedList", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ArrayList<SongAndRating>> getSongAndRating(HttpServletRequest request) {
		UsernamePasswordAuthenticationToken userToken = (UsernamePasswordAuthenticationToken)request.getUserPrincipal();
		if (userToken == null) {
			System.out.println("Null user");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value())
					.body(new ArrayList<SongAndRating>());
		}
		
		String userId = ((UserInfo)(userToken.getPrincipal())).getId();
		System.out.println(userId);
		try {
			ArrayList<SongAndRating> list = (ArrayList<SongAndRating>) songsDao.getSongsAndRatingsbyUser(userId);
			return ResponseEntity.status(HttpStatus.OK.value())
					.body(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.body(new ArrayList<SongAndRating>());
		}
	}
	
	@RequestMapping(value="/api/song/list", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<ArrayList<Song>> getSongList(HttpServletRequest request) {
		UsernamePasswordAuthenticationToken userToken = (UsernamePasswordAuthenticationToken)request.getUserPrincipal();
		if (userToken == null) {
			System.out.println("Null user");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value())
					.body(new ArrayList<Song>());
		}
		
		String userId = ((UserInfo)(userToken.getPrincipal())).getId();
		System.out.println(userId);
		try {
			ArrayList<Song> list = (ArrayList<Song>) songsDao.getSongList(userId);
			return ResponseEntity.status(HttpStatus.OK.value())
					.body(list);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.body(new ArrayList<Song>());
		}
	}
	
	@RequestMapping(value="/api/message/add", method=RequestMethod.POST)
	@ResponseBody
	public ResponseEntity<ServiceResponse> addMessage(HttpServletRequest request, @RequestBody Message message) {
		UsernamePasswordAuthenticationToken userToken = (UsernamePasswordAuthenticationToken)request.getUserPrincipal();
		if (userToken == null) {
			System.out.println("Null user");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value())
					.body(new ServiceResponse(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase()));
		}
		
		String userId = ((UserInfo)(userToken.getPrincipal())).getId();
		message.setFromId(userId);
		message.setTs(new Timestamp(new Date().getTime()));
		try {
			messageDao.addMessage(message);
			return ResponseEntity.status(HttpStatus.OK.value())
					.body(new ServiceResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase()));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.body(new ServiceResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
		}
	}
	
	@RequestMapping(value="/api/message/getAll", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<UserMessageResponse> getAllMessages(HttpServletRequest request) {
		return getAllMessages(request, 0, 10);
	}
	
	@RequestMapping(value="/api/message/getAll/{from}/{to}", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<UserMessageResponse> getAllMessages(HttpServletRequest request, @PathVariable int from, @PathVariable int to) {
		UsernamePasswordAuthenticationToken userToken = (UsernamePasswordAuthenticationToken)request.getUserPrincipal();
		if (userToken == null) {
			System.out.println("Null user");
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value())
					.body(new UserMessageResponse(HttpStatus.UNAUTHORIZED.value(), HttpStatus.UNAUTHORIZED.getReasonPhrase()));
		}
		
		String userId = ((UserInfo)(userToken.getPrincipal())).getId();
		try {
			ArrayList<UserMessage> res = (ArrayList<UserMessage>) messageDao.getAllMessages(userId, Math.max(0, from), to);
			return ResponseEntity.status(HttpStatus.OK.value())
					.body(new UserMessageResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), res));
		} catch (Exception e) {
			e.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.body(new UserMessageResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
		}
	}
	
	@RequestMapping(value="/api/userlist/{key}", method=RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<UserAutocompleteResponse> getUserList(@PathVariable String key) {
		try {
			ArrayList<UserSearch> res = (ArrayList<UserSearch>) userDao.getUserList(key.toLowerCase());
			System.out.println(res.size());
			for (UserSearch u : res) {
				System.out.println(u.getFirstName());
			}
			return ResponseEntity.status(HttpStatus.OK.value())
					.body(new UserAutocompleteResponse(HttpStatus.OK.value(), HttpStatus.OK.getReasonPhrase(), res));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR.value())
					.body(new UserAutocompleteResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), HttpStatus.INTERNAL_SERVER_ERROR.getReasonPhrase()));
		}
	}
	
}
