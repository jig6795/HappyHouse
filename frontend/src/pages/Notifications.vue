<template>
  <div class="content" style="background-color: Azure">
    <div class="container-fluid">
      <card v-if="getAccessToken">
        <div class="container-fluid">
          <h1 class="nameColor">Notifications</h1>
        </div>
        <br />
        <div class="container-fluid">
          <h5 style="text-align:center">
            <strong>각 항목을 클릭해서 내용을 확인해보세요.</strong>
          </h5>
        </div>
        <br />
        <div class="container">
          <div class="input-group mb-3">
            <b-form-input
              id="inputSearchWord"
              v-model="searchWord"
              type="text"
              class="form-control"
              placeholder="Search"
            ></b-form-input>
            <div class="input-group-append">
              <b-button
                id="btnSearchWord"
                class="btn btn-success"
                @click="ListSearch"
                >Go</b-button
              >
            </div>
          </div>

          <table class="table table-hover">
            <thead>
              <tr>
                <th>No</th>
                <th>제목</th>
                <th>작성자</th>
                <th>작성일시</th>
                <th>조회수</th>
              </tr>
            </thead>
            <tbody>
              <tr
                v-for="(item, index) in getBoardItem"
                :key="index"
                @click="ListDetail(item.boardId)"
              >
                <td>{{ item.boardId }}</td>
                <td>{{ item.title }}</td>
                <td>{{ item.userName }}</td>
                <td>
                  {{ item.regDt.date.year }}.{{ item.regDt.date.month }}.{{
                    item.regDt.date.day
                  }}
                  &nbsp&nbsp{{ item.regDt.time.hour }}:{{
                    item.regDt.time.minute
                  }}:{{ item.regDt.time.second }}
                </td>
                <td>{{ item.readCount }}</td>
              </tr>
            </tbody>
          </table>

          <page-link v-if="check"/>
        </div>
      </card>

      <card v-else>
        <h1><strong>로그인 후 사용하세요!</strong></h1>
      </card>
    </div>
  </div>
</template>

<script>
import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";

Vue.use(Vuex);
import Card from "src/components/Cards/Card.vue";
import PageLink from "src/components/PageLink.vue";
import { mapGetters } from "vuex";

const SERVER_URL = "http://localhost:8000/vue";

export default {
  data() {
    return {
      searchWord: "",
      check: false
    };
  },
  components: {
    Card,
    PageLink
  },
  computed: {
    ...mapGetters(["getAccessToken", "getBoardItem","getBoardItemCount"])
  },
  beforeMount() {
    // this.$store.dispatch("sendWord", "");
    this.$store.dispatch("LIST", "").then(() => this.check = true);
  },
  methods: {
    ListSearch: function() {
      // this.$store.dispatch("sendWord", this.searchWord);
      // console.log("listSearch : " + this.searchWord);
      this.$store.dispatch("LIST", this.searchWord).then(() => this.check = true);
    },
    ListDetail: function(boardId) {
      this.$store
        .dispatch("ListDetail", boardId)
        .then(() => this.$router.replace(`NoticeDetail`))
        .catch();
    }
  }
};
</script>
<style lang="scss">
.nameColor {
  font-weight: bold;
  text-align: center;
  text-shadow: 3px 3px #8c7b83;
  color: #eeaeca;
}
</style>
