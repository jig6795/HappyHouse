import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";

Vue.use(Vuex);

const SERVER_URL = "http://localhost:8000/vue";

export default new Vuex.Store({
  state: {
    accessToken: null,
    userEmail: "",
    userName: "",
    userId: "",
    userPassword: "",
    userManager: "",
    user: {
      SearchWord: "",
      items: [],
      detailItems: []
    },
    map: {
      sido: [],
      gugun: [],
      dong: [],
      apt: [],
      aptlist: [],
      totalDong: [],
      donglist: [],
      marker: [],
      mapstate: "",
      mapstateApt: "",
      deal: [],
      marker: [],
    },
    board: {
      searchWord: "",
      boardItems: [],
      boardDetailItems: [],
      boardItemCount: 0
    }
    // boardSearchWord: ""
  },
  getters: {
    getAccessToken(state) {
      return state.accessToken;
    },
    getUserEmail(state) {
      return state.userEmail;
    },
    getUserName(state) {
      return state.userName;
    },
    getUserId(state) {
      return state.userId;
    },
    getUseritems(state) {
      return state.user.items;
    },
    getUserDetail(state) {
      return state.user.detailItems;
    },
    getUserManager(state) {
      return state.userManager;
    },
    getBoardItem(state) {
      return state.board.boardItems;
    },
    getBoardItemDetail(state) {
      return state.board.boardDetailItems;
    },
    getBoardItemCount(state) {
      // console.log(state.board.boardItemCount);
      return state.board.boardItemCount;
    },
    getSearchMap(state) {
      return state.map.sido;
    },
    getMapstate(state) {
      return state.map.mapstate;
    },
    getMapstateApt(state) {
      return state.map.mapstateApt;
    },
    getSearchMapGugun(state) {
      return state.map.gugun;
    },
    getSearchMapDong(state) {
      return state.map.dong;
    },
    getSearchMapApt(state) {
      return state.map.apt;
    },
    getSearchMapAptList(state) {
      return state.map.aptlist;
    },
    getSearchMapMarker(state) {
      return state.map.marker;
    },
    getSearchMapDongList(state) {
      return state.map.donglist;
    },
    getSearchMapTotalDong(state) {
      return state.map.totalDong;
    },
    getSearchDeal(state) {
      return state.map.deal;
    },
    getSearchMapMarker(state) {
      return state.map.marker;
    },
    // getSearchWord(state) {
    //   console.log(get);
    //   return state.boardSearchWord;
    // }
  },
  mutations: {
    LOGIN(state, payload) {
      state.accessToken = payload["auth-token"];
      state.userEmail = payload["user-Email"];
      state.userName = payload["user-Name"];
      state.userManager = payload["user-Manager"];
    },
    LOGOUT(state) {
      state.accessToken = null;
      state.userEmail = "";
      state.userName = "";
    },
    SearchUser(state, payload) {
      state.user.items = payload;
      // console.log(state.user.items);
    },
    UserDetail(state, payload) {
      state.user.detailItems = payload;
      // console.log(state.user.detailItems);
    },
    SearchUserWord(state, payload) {
      state.user.items = payload;
      // console.log(state.user.items);
    },
    LIST(state, payload) {
      state.board.boardItems = payload;
    },
    ListCount(state, payload) {
      state.board.boardItemCount = payload;
      // console.log("mutations : " + state.board.boardItemCount);
    },
    ListDetail(state, payload) {
      state.board.boardDetailItems = payload;
      // console.log(state.board.boardDetailItems.dto);
    },
    SearchMap(state, payload) {
      state.map.sido = payload;
      // console.log(state.map.code);
    },
    MapState(state, payload) {
      state.map.mapstate = payload;
      // console.log(state.map.code);
    },
    MapStateApt(state, payload) {
      state.map.mapstateApt = payload;
      // console.log(state.map.code);
    },
    SearchMapGugun(state, payload) {
      state.map.gugun = payload;
      // console.log(state.map.gugun);
    },
    SearchMapDong(state, payload) {
      state.map.dong = payload;
      // console.log(state.map.gugun);
    },
    SearchMapApt(state, payload) {
      state.map.apt = payload;
      // console.log(state.map.gugun);
    },
    SearchMapMarker(state, payload) {
      state.map.marker = payload;
      // console.log(state.map.gugun);
    },
    SearchMapAptList(state, payload) {
      state.map.aptlist = payload;
      // console.log(state.map.gugun);
    },
    SearchMapDongList(state, payload) {
      state.map.donglist = payload;
      // console.log(state.map.gugun);
    },
    SearchMapTotalDong(state, payload) {
      state.map.totalDong = payload;
      // console.log(state.map.gugun);
    },
    SearchMapMarker(state, payload) {
      state.map.marker = payload;
      // console.log(state.map.gugun);
    },
    SearchDeal(state, payload) {
      state.map.deal = payload;
      // console.log(state.map.code);
    }
    // sendWord(state, payload) {
    //   state.boardSearchWord = payload;
    //   console.log(state.boardSearchWord);
    // }
  },

  actions: {
    LOGIN(context, user) {
      return axios
        .post(`${SERVER_URL}/user/confirm/login`, user)
        .then(response => {
          context.commit("LOGIN", response.data);
          axios.defaults.headers.common[
            "auth-token"
          ] = `${response.data["auth-token"]}`;
        });
    },
    JOIN(context, user) {
      return axios.post(`${SERVER_URL}/register`, user).then(response => {
        // context.commit('JOIN', response.data);
        // axios.defaults.headers.common['auth-token'] = `${response.data['auth-token']}`;
      });
    },
    UserUpdate(context, user) {
      return axios.post(`${SERVER_URL}/update`, user).then(response => {
        // context.commit('JOIN', response.data);
        // axios.defaults.headers.common['auth-token'] = `${response.data['auth-token']}`;
      });
    },
    UserDelete(context, user) {
      return axios
        .delete(`${SERVER_URL}/delete/` + user.userEmail)
        .then(response => {
          // context.commit('JOIN', response.data);
          // axios.defaults.headers.common['auth-token'] = `${response.data['auth-token']}`;
        });
    },
    SearchUser(context) {
      // console.log("searchuser axios");
      return axios.get(`${SERVER_URL}/searchuser`).then(response => {
        context.commit("SearchUser", response.data);
        // console.log(response.data);
        // axios.defaults.headers.common['auth-token'] = `${response.data['auth-token']}`;
      });
    },
    SearchUserWord(context, searchword) {
      // console.log("searchuserword axios");
      return axios
        .get(`${SERVER_URL}/searchuserword/` + searchword)
        .then(response => {
          context.commit("SearchUserWord", response.data);
          // console.log(response.data);
          // axios.defaults.headers.common['auth-token'] = `${response.data['auth-token']}`;
        });
    },
    UserDetail(context, userEmail) {
      // console.log("UserDetail axios");
      return axios.get(`${SERVER_URL}/detail/` + userEmail).then(response => {
        context.commit("UserDetail", response.data);
        // console.log(response.data);
        // axios.defaults.headers.common['auth-token'] = `${response.data['auth-token']}`;
      });
    },
    LIST(context, searchWord) {
      // console.log("enter list");
      // console.log(searchWord);
      if (searchWord == "") {
        searchWord = "total";
      }
      return axios.get(`${SERVER_URL}/boards/` + searchWord).then(response => {
        // console.log("Print");
        // console.log(response.data);
        context.commit("LIST", response.data.list);
        // console.log(response.data.count);
        context.commit("ListCount", response.data.count);
        // console.log(response.data.list);
      });
    },
    // sendWord(context, searchWord) {
    //   // console.log("sendWord : " + searchWord);
    //   if (searchWord == "") {
    //     searchWord = "total";
    //   }
    //   console.log("sendWord : " + searchWord);
    //   context.commit("sendWord", searchWord);
    //   console.log("commit Complete");
    // },
    ListDetail(context, boardId) {
      // console.log("UserDetail axios");
      return axios
        .get(`${SERVER_URL}/boardsDetail/` + boardId)
        .then(response => {
          context.commit("ListDetail", response.data);
          // console.log(response.data);
          // axios.defaults.headers.common['auth-token'] = `${response.data['auth-token']}`;
        });
    },
    DeleteNotice(context, boardId) {
      return axios.delete(`${SERVER_URL}/boards/` + boardId).then(response => {
        // context.commit('JOIN', response.data);
        // axios.defaults.headers.common['auth-token'] = `${response.data['auth-token']}`;
      });
    },
    LOGOUT(context) {
      context.commit("LOGOUT");
      axios.defaults.headers.common["auth-token"] = undefined;
    },
    SearchMap(context, Act) {
      // console.log("SearchMap axios");
      return axios
        .get(`${SERVER_URL}/map`, { params: { act: "sido" } })
        .then(response => {
          context.commit("SearchMap", response.data);
          // console.log(response.data);
          // axios.defaults.headers.common['auth-token'] = `${response.data['auth-token']}`;
        });
    },
    SearchMapGugun(context, selectcode) {
      var params = new URLSearchParams();
      params.append("act", "gugun");
      params.append("sido", selectcode);
      // console.log("SearchMapGugun axios");
      return axios
        .get(`${SERVER_URL}/map`, { params: params })
        .then(response => {
          context.commit("SearchMapGugun", response.data);
          // console.log(response.data);
          // axios.defaults.headers.common['auth-token'] = `${response.data['auth-token']}`;
        });
    },
    SearchMapTotalDong(context, selectcode) {
      var params = new URLSearchParams();
      params.append("act", "totalDong");
      params.append("gugun", selectcode);
      // console.log("SearchMapTotalDong axios");
      return axios
        .get(`${SERVER_URL}/map`, { params: params })
        .then(response => {
          context.commit("SearchMapTotalDong", response.data);
          // console.log(response.data);
          // axios.defaults.headers.common['auth-token'] = `${response.data['auth-token']}`;
        });
    },
    SearchMapApt(context, selectcode) {
      var params = new URLSearchParams();
      params.append("act", "dong2");
      params.append("gugun", selectcode);
      // console.log("SearchMapApt axios");
      return axios
        .get(`${SERVER_URL}/map`, { params: params })
        .then(response => {
          context.commit("SearchMapApt", response.data);
          // console.log(response.data);
          // axios.defaults.headers.common['auth-token'] = `${response.data['auth-token']}`;
        });
    },
    SearchMapDong(context, selectcode) {
      var params = new URLSearchParams();
      params.append("act", "dong");
      params.append("gugun", selectcode);
      // console.log("SearchMapDong axios");
      return axios
        .get(`${SERVER_URL}/map`, { params: params })
        .then(response => {
          context.commit("SearchMapDong", response.data);
          // console.log(response.data);
          // axios.defaults.headers.common['auth-token'] = `${response.data['auth-token']}`;
        });
    },
    SearchMapAptList(context, selectAptName) {
      var params = new URLSearchParams();
      params.append("act", "AptName");
      params.append("AptName", selectAptName);
      // console.log("SearchMapAptList axios");
      return axios
        .get(`${SERVER_URL}/map`, { params: params })
        .then(response => {
          context.commit("SearchMapAptList", response.data);
          context.commit("MapStateApt", 1);
          // context.commit('SearchMapMarker', {"response.data.lat","response.data.lng" );
          // console.log(response.data);
          // axios.defaults.headers.common['auth-token'] = `${response.data['auth-token']}`;
        });
    },
    SearchMapDongList(context, selectcode) {
      var params = new URLSearchParams();
      params.append("act", "apt");
      params.append("dong", selectcode);
      // console.log("SearchMapDongList axios");
      return axios
        .get(`${SERVER_URL}/map`, { params: params })
        .then(response => {
          context.commit("SearchMapDongList", response.data);
          context.commit("MapState", 1);
          // console.log(response.data);
          // axios.defaults.headers.common['auth-token'] = `${response.data['auth-token']}`;
        });
    },
    SearchDeal(context, selectAptName) {
      var params = new URLSearchParams();
      params.append("act", "Deal");
      params.append("AptName", selectAptName);
      // console.log("SearchMapAptList axios");
      return axios
        .get(`${SERVER_URL}/map`, { params: params })
        .then(response => {
          console.log(response.data);
          context.commit("SearchDeal", response.data);
          // context.commit("MapStateApt", 1);
          // context.commit('SearchMapMarker', {"response.data.lat","response.data.lng" );
          // console.log(response.data);
          // axios.defaults.headers.common['auth-token'] = `${response.data['auth-token']}`;
        });
    }
  },
  modules: {}
});
