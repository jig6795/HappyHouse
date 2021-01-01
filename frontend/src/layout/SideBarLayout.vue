<template>
  <div class="wrapper">
    <side-bar>
      <sidebar-link to="/admin/overview">
        <i class="nc-icon nc-grid-45"></i>
        <p>Main</p>
      </sidebar-link>
      <sidebar-link to="/admin/maps">
        <i class="nc-icon nc-square-pin"></i>
        <p>Maps</p>
      </sidebar-link>
      <sidebar-link to="/admin/all">
        <i class="nc-icon nc-notes"></i>
        <p>All List</p>
      </sidebar-link>
      <sidebar-link to="/admin/apart">
        <i class="nc-icon nc-zoom-split"></i>
        <p>Apartment Search</p>
      </sidebar-link>
      <sidebar-link to="/admin/region">
        <i class="nc-icon nc-zoom-split"></i>
        <p>Region Search</p>
      </sidebar-link>
      <sidebar-link to="/admin/notifications">
        <i class="nc-icon nc-notification-70"></i>
        <p>Notifications</p>
      </sidebar-link>
      <sidebar-link to="/admin/user">
        <i class="nc-icon nc-settings-90"></i>
        <p>User Management</p>
      </sidebar-link>
    </side-bar>
    <div class="main-panel">
      <top-navbar></top-navbar>
      <dashboard-content @click="toggleSidebar">
      </dashboard-content>
      <content-footer></content-footer>
    </div>
  </div>
</template>
<style lang="scss">

</style>
<script>
  import TopNavbar from './TopNavbar.vue'
  import ContentFooter from './ContentFooter.vue'
  import DashboardContent from './Content.vue'
  export default {
    components: {
      TopNavbar,
      ContentFooter,
      DashboardContent,
    },
    methods: {
      toggleSidebar () {
        if (this.$sidebar.showSidebar) {
          this.$sidebar.displaySidebar(false)
        }
      }
    }
  }

</script>
