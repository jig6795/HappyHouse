<template>
  <nav class="navbar navbar-expand-lg">
    <div class="container-fluid">
      <a class="navbar-brand" href="#"><i class="nc-icon nc-bank"></i>&nbsp&nbspWelcome to HappyHouse</a>
      <div class="collapse navbar-collapse justify-content-end">
        <b-collapse id="nav-collapse" is-nav>
          <b-navbar-nav class="ml-auto" v-if="getAccessToken">
            <b-nav-item><strong><i class="nc-icon nc-circle-09"></i>&nbsp&nbsp{{ getUserName }}</strong></b-nav-item>
            <b-nav-item><router-link to="me"><i class="nc-icon nc-badge"></i>&nbsp 마이페이지</router-link></b-nav-item>
            <b-nav-item @click.prevent="onClickLogout"><i class="nc-icon nc-button-power"></i>&nbsp 로그아웃</b-nav-item>
            <!-- <b-nav-item href="/me">내정보보기</b-nav-item>
            <b-nav-item href="/logout">로그아웃</b-nav-item> -->
          </b-navbar-nav>
          <b-navbar-nav class="ml-auto" v-else>
            <b-nav-item><router-link to="login"><i class="nc-icon nc-circle-09"></i>&nbsp&nbsp로그인</router-link></b-nav-item>
            <b-nav-item><router-link to="join"><i class="nc-icon nc-key-25"></i>&nbsp&nbsp회원가입</router-link></b-nav-item>
            <!-- <b-nav-item href="/login">로그인</b-nav-item>
            <b-nav-item href="/join">회원가입</b-nav-item> -->
          </b-navbar-nav>
        </b-collapse>
      </div>
    </div>
  </nav>
</template>

<script>
//mapGetters 등록
import { mapGetters } from "vuex";

export default {
  data() {
    return {
      isLogin: false
    };
  },
  computed: {
    ...mapGetters(["getAccessToken", "getUserEmail", "getUserName"]),
    routeName() {
      const { name } = this.$route;
      return this.capitalizeFirstLetter(name);
    }
  },
  methods: {
    capitalizeFirstLetter(string) {
      return string.charAt(0).toUpperCase() + string.slice(1);
    },
    toggleSidebar() {
      this.$sidebar.displaySidebar(!this.$sidebar.showSidebar);
    },
    hideSidebar() {
      this.$sidebar.displaySidebar(false);
    },
    onClickLogout() {
      this.$store
        .dispatch("LOGOUT")
        .then(() => this.$router.replace("/").catch(() => {}));
    }
  }
};
</script>
<style></style>
