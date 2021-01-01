<template>
  <div class="content" style="background-color: Azure;">
    <b-container class="mt-4">
      <b-row>
        <b-col></b-col>
        <b-col cols="8">
          <b-jumbotron style="background-color:transparent; text-align:center">
            <template #header
              ><i class="nc-icon nc-badge"></i> My Page</template
            >
            <br>
            <div style="font-size:20px; text-align:center">
              <strong><i class="nc-icon nc-chat-round"></i> {{ getUserDetail.userName }}님 환영합니다.</strong>
            </div>
            <b-container class="mt-4">
              <b-row>
                <b-col cols="2"></b-col>
                <b-col cols="2" align-self="end"></b-col>
                <b-col cols="4" align-self="start"><img src="/img/default-avatar.png" alt="picture"></b-col>
                <b-col cols="2"></b-col>
              </b-row>
              <hr class="mt-4" />
              <b-row>
                <b-col cols="2"></b-col>
                <b-col cols="3" align-self="end"><i class="nc-icon nc-email-83"></i>&nbsp Email</b-col>
                <b-col cols="4" align-self="start">{{ getUserEmail }}</b-col>
                <b-col cols="2"></b-col>
              </b-row>
              <hr class="mt-4" />
              <b-row>
                <b-col cols="2"></b-col>
                <b-col cols="3" align-self="end"><i class="nc-icon nc-watch-time"></i>&nbsp JoinDate</b-col>
                <b-col cols="4" align-self="start">{{
                  getUserDetail.userRegisterDate.substring(0,getUserDetail.userRegisterDate.length-11)
                }}</b-col>
                <b-col cols="2"></b-col>
              </b-row>
              <hr class="mt-4" />
            </b-container>
            <b-button 
            variant="transparent" 
            href="#" 
            style="float: right"
              ><router-link to="UserDelete"><i class="nc-icon nc-circle-09"></i>&nbsp 가입해지 신청</router-link></b-button>
              
            <b-button
              variant="transparent"
              href="#"
              class="mr-1"
              style="float: right"
              >
              <router-link to="UserUpdate"><i class="nc-icon nc-circle-09"></i>&nbsp 회원정보 수정</router-link>
              </b-button>
              
          </b-jumbotron>
        </b-col>
        <b-col></b-col>
      </b-row>
    </b-container>
  </div>

  <!-- <h2>내정보보기</h2>
    이름 : {{ user.username }}<br />
    이메일 : {{ user.email }}<br />
    주소 : {{ user.address }}<br />
    가입일 : {{ user.joindate }}<br />
  </div> -->
</template>

<script>
import { mapGetters } from "vuex";


export default {
  data() {
    return {
      userEmail : this.getUserEmail,
    };
  },
    computed: {
    ...mapGetters(["getUserDetail","getAccessToken","getUserEmail"])
  },
  created() {
      this.$store
        .dispatch("UserDetail", this.getUserEmail)

    }
}
</script>

<style>
</style>
