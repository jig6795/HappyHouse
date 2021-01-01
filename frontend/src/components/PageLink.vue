<template>
  <div>
    <ul class="pagination justify-content-center">
      <li class="page-item" v-if="prev">
        <router-link :to="`notifications?no=${ (startPageIndex - 1) * listRowCount }`" class="page-link" aria-label="Previous" @click.native="movePage(startPageIndex - 1)">
          <span aria-hidden="true">&laquo;</span>
        </router-link>
      </li>

      <li v-for="index in endPageIndex-startPageIndex + 1 " :key="index" class="page-item" :class="{active:( (startPageIndex + index - 1) == currentPageIndex)}">
        <router-link :to="`notifications?no=${ (startPageIndex + index - 1) * listRowCount }`" class="page-link"  @click.native="movePage(startPageIndex + index - 1)">{{ startPageIndex + index - 1 }}</router-link>
        <!-- <a class="page-link" href="javascript:movePage(' + i + ')">' + i + '</a> -->
      </li>

      <li class="page-item" v-if="next">
        <router-link :to="`notifications?no=${ (endPageIndex + 1) * listRowCount }`" class="page-link" aria-label="Next"  @click.native="movePage(endPageIndex + 1)">
          <span aria-hidden="true">&raquo;</span>
        </router-link>
      </li>
    </ul>
  </div>
</template>

<script>
import Vue from "vue";
import Vuex from "vuex";
import axios from "axios";
const SERVER_URL = "http://localhost:8000/vue";

Vue.use(Vuex);

export default {
  data() {
    return {
      totalListItemCount: 0,
      listRowCount: 10,
      pageLinkCount: 10,
      currentPageIndex: 1,

      pageCount: 0,
      startPageIndex: 0,
      endPageIndex: 0,
      prev: false,
      next: false,
    };
  },
  methods: {
    movePage( param ) {
      this.currentPageIndex = param;
      this.initComponent();
    },
    initComponent(){
      // Getters 사용하는 방법!
      this.totalListItemCount = this.$store.state.board.boardItemCount;
      this.initUI();
    },
    initUI(){
      this.pageCount = Math.ceil(this.totalListItemCount/this.listRowCount);

      if( (this.currentPageIndex % this.pageLinkCount) == 0 ){
        this.startPageIndex = ((this.currentPageIndex / this.pageLinkCount)-1)*this.pageLinkCount + 1
      }else{
        this.startPageIndex = Math.floor(this.currentPageIndex / this.pageLinkCount)*this.pageLinkCount + 1
      }

      if( (this.currentPageIndex % this.pageLinkCount) == 0 ){ //10, 20...맨마지막
        this.endPageIndex = ((this.currentPageIndex / this.pageLinkCount)-1)*this.pageLinkCount + this.pageLinkCount
      }else{
        this.endPageIndex =  Math.floor(this.currentPageIndex / this.pageLinkCount)*this.pageLinkCount + this.pageLinkCount;
      }

      if(this.endPageIndex > this.pageCount){
        this.endPageIndex = this.pageCount
      }

      if( this.currentPageIndex <= this.pageLinkCount ){
        this.prev = false;
      }else{
        this.prev = true;
      }

      if(this.endPageIndex >= this.pageCount){
        this.endPageIndex = this.pageCount;
        this.next = false;
      }else{
        this.next = true;
      }
    }
  },
  watch:{
    currentPageIndex: function(){
      this.initUI();
    }
  },
  created() {
    this.initComponent();
  },
  mounted(){
    this.$router.push('Notifications?no=' + this.listRowCount)
  }
};
</script>

<style>
  
</style>