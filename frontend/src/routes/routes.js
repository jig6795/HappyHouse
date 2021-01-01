import SideBarLayout from "../layout/SideBarLayout.vue";
// GeneralViews
import NotFound from "../pages/NotFoundPage.vue";

// Admin pages
import Main from "src/pages/Main.vue";

import Login from "src/pages/Login.vue";
import Me from 'src/pages/Me.vue';
import Join from 'src/pages/Join.vue';

import Maps from "src/pages/Maps.vue";
import AllList from "src/pages/AllList.vue";
import ApartSearch from "src/pages/ApartSearch.vue";
import RegionSearch from "src/pages/RegionSearch.vue";

import Notifications from "src/pages/Notifications.vue";
import NoticeDetail from 'src/pages/NoticeDetail.vue';

import UserManagement from "src/pages/UserManagement.vue";
import UserDetail from 'src/pages/User/UserDetail.vue';
import UserUpdate from 'src/pages/User/UserUpdate.vue';
import UserDelete from 'src/pages/User/UserDelete.vue';

// const requireAuth = () => (to, from, next) => {
//   const nextRoute = to.path;

//   if (store.getters.getAccessToken) {
//     return next();
//   } else next("login" + nextRoute);
// };

const routes = [
  {
    path: "/",
    component: SideBarLayout,
    redirect: "/admin/overview"
  },
  {
    path: "/admin",
    component: SideBarLayout,
    redirect: "/admin/overview",
    children: [
      {
        path: "overview",
        name: "Overview",
        component: Main
      },
      {
        path: "user",
        name: "User",
        component: UserManagement
      },
      {
        path: "all",
        name: "All List",
        component: AllList
      },
      {
        path: "apart",
        name: "Apart Search",
        component: ApartSearch
      },
      {
        path: "region",
        name: "Region Search",
        component: RegionSearch
      },
      {
        path: "maps",
        name: "Maps",
        component: Maps
      },
      {
        path: "notifications",
        name: "Notifications",
        component: Notifications
      },
      {
        path: "noticeDetail",
        name: "NoticeDetail",
        component: NoticeDetail
      },
      {
        path: "login",
        name: "Login",
        component: Login,
      },
      {
        path: "me",
        name: "Me",
        component: Me,
        //beforeEnter: requireAuth()
      },
      {
        path: "join",
        name: "Join",
        component: Join,
      },
      {
        path: "userUpdate",
        name: "UserUpdate",
        component: UserUpdate,
      },
      {
        path: "userDelete",
        name: "UserDelete",
        component: UserDelete,
      },
      {
        path: "userDetail",
        name: "UserDetail",
        component: UserDetail,
      },
    ]
  },
  { path: "*", component: NotFound }
];

/**
 * Asynchronously load view (Webpack Lazy loading compatible)
 * The specified component must be inside the Views folder
 * @param  {string} name  the filename (basename) of the view to load.
function view(name) {
   var res= require('../components/Dashboard/Views/' + name + '.vue');
   return res;
};**/

export default routes;
