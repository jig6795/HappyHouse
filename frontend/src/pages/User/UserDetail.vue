<template>
  <div class="content" style="background-color: Azure;">
    <div class="container">
      <card>
        <div class="container">
          <h1 class="nameColor">회원 상세 정보</h1>
        </div>
        <div style="text-align:center">
          <br>
          <img src="/img/profile.png" />
          <br><br>
          <ul style="list-style:none">
            <li>
              <strong>회원번호 : {{ getUserDetail.userSeq }}</strong>
            </li>
            <li>
              <strong>이름 : {{ getUserDetail.userName }}</strong>
            </li>
            <li>
              <strong>Email : {{ getUserDetail.userEmail }}</strong>
            </li>
            <li>
              <strong>비밀번호 : {{ getUserDetail.userPassword }}</strong>
            </li>
            <li>
              <strong>가입날짜 : {{ getUserDetail.userRegisterDate }}</strong>
            </li>
          </ul>
        </div>
        <b-button
          type="button"
          variant="contained"
          class="m-1"
          style="float: right"
        >
          <router-link to="user"
            ><i class="nc-icon nc-circle-09"></i> 회원목록으로 가기</router-link
          >
        </b-button>
      </card>
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
  computed: {
    ...mapGetters(["getUserDetail"])
  }
};
</script>
<style lang="scss"></style>
