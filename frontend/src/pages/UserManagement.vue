<template>
  <div class="content" style="background-color: Azure;">
    <div class="container-fluid">
      <div class="row">
        <div class="col-md-12">
          <card v-if="getUserManager">
            <div class="container">
              <h1 class="nameColor">회원 조회</h1>
            </div>
            <br />
            <div class="container">
              <div class="input-group mb-3">
                <input
                  id="inputSearchWord"
                  type="text"
                  class="form-control"
                  placeholder="Search"
                  v-model="searchword"
                  required
                />
                <div class="input-group-append">
                  <button
                    id="btnSearchWord"
                    class="btn btn-success"
                    @keypress.enter="SearchUserWord"
                    @click="SearchUserWord"
                  >
                    검색
                  </button>
                </div>
              </div>

              <table class="table table-hover">
                <thead>
                  <tr>
                    <th>No</th>
                    <th>이름</th>
                    <th>비밀번호</th>
                    <th>이메일</th>
                    <th>가입일</th>
                  </tr>
                </thead>
                <tbody>
                  <tr
                    v-for="(item, index) in getUseritems"
                    :key="index"
                    @click="UserDetail(item.userEmail)"
                  >
                    <!-- <td>{{item}}, {{ index }}</td> -->
                    <td>{{ item.userSeq }}</td>
                    <td>{{ item.userName }}</td>
                    <td>{{ item.userPassword }}</td>
                    <td>{{ item.userEmail }}</td>
                    <td>{{ item.userRegisterDate }}</td>
                  </tr>
                </tbody>
              </table>

              <!-- <div id="paginationWrapper"></div> -->
            </div>
          </card>
          <card v-else>
            <h1><strong>관리자를 위한 페이지입니다.</strong></h1>
          </card>
        </div>
      </div>
    </div>
  </div>
</template>
<script>
import Card from "src/components/Cards/Card.vue";
import VueRouter from "vue-router";
import { mapGetters } from "vuex";
import Vue from "vue";
export default {
  components: {
    Card
  },
  data: function() {
    return {
      searchword: ""
      // userEmail: "",
    };
  },
  computed: {
    ...mapGetters(["getAccessToken", "getUseritems", "getUserManager"])
  },
  // filters: {
  //   makeDateStr: function(value) {
  //     // console.log(value.day);
  //     if (value.type == ".") {
  //       return (
  //         value.year +
  //         "." +
  //         (value.month < 10 ? "0" + value.month : value.month) +
  //         "." +
  //         (value.day < 10 ? "0" + value.day : value.day)
  //       );
  //     }
  //   },
  //   makeTimeStr: function(hour, minute, second, type) {
  //     if (type == ":") {
  //       return (
  //         hour +
  //         ":" +
  //         (minute < 10 ? "0" + minute : minute) +
  //         ":" +
  //         (second < 10 ? "0" + second : second)
  //       );
  //     }
  //   }
  // },
  beforeMount() {
    this.$store.dispatch("SearchUser");
  },
  methods: {
    SearchUserWord: function() {
      this.$store.dispatch("SearchUserWord", this.searchword);
    },
    UserDetail: function(userEmail) {
      this.$store
        .dispatch("UserDetail", userEmail)
        .then(() => this.$router.replace(`UserDetail`))
        .catch();
    }
  }
};
</script>
<style>
</style>
