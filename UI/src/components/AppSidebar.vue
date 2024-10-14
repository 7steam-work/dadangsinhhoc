<script setup>

import { AppSidebarNav } from '@/components/AppSidebarNav.js'
import { useSidebarStore } from '@/stores/sidebar.js'
import logoAdmin from '@/assets/images/logoAdmin.png'

const sidebar = useSidebarStore()
</script>

<template>
  <CSidebar class="border-end" colorScheme="dark" position="fixed" :unfoldable="sidebar.unfoldable"
    :visible="sidebar.visible" @visible-change="(value) => sidebar.toggleVisible(value)">
    <CSidebarHeader class="border-bottom">
      <RouterLink custom to="/dashboard" v-slot="{ href, navigate }">
        <CSidebarBrand style="text-decoration: none;" v-bind="$attrs" as="a" :href="href" @click="navigate">
          <div style="font-size: 1rem; color: #1E7C69;" class="d-flex justify-content-center align-items-center mt-2">
            <img :src="logoAdmin" alt="logo" class="me-1" height="25"><span
              style="color:#E2E3E4; font-size: 1rem; font-weight:500">Admin</span>Vue.js
          </div>
        </CSidebarBrand>
      </RouterLink>
      <CCloseButton class="d-lg-none" dark @click="sidebar.toggleVisible()" />
    </CSidebarHeader>
    <AppSidebarNav />
    <CSidebarFooter class="border-top d-none d-lg-flex">
      <CSidebarToggler @click="sidebar.toggleUnfoldable()" />
    </CSidebarFooter>
  </CSidebar>
</template>
