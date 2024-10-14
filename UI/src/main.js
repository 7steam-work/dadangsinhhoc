import { createApp } from 'vue'
import { createPinia } from 'pinia'

import App from './App.vue'
import router from './router'

import CoreuiVue from '@coreui/vue'
import CIcon from '@coreui/icons-vue'
import { iconsSet as icons } from '@/assets/icons'
import DocsExample from '@/components/DocsExample'
import { CkeditorPlugin } from '@ckeditor/ckeditor5-vue';
const app = createApp(App)
app.use(createPinia())
app.use(router)
app.use(CoreuiVue)
app.use(CkeditorPlugin )
app.provide('icons', icons)
app.component('CIcon', CIcon)
app.component('DocsExample', DocsExample)

app.mount('#app')
