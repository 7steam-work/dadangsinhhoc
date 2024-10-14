<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Login</title>
    <meta name="description" content="">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/tailwindcss/2.2.19/tailwind.min.css" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css2?family=Lato:wght@400;700&display=swap" rel="stylesheet">
    <style>
        .body-bg {
            background-color: #065f46;
            background-image: linear-gradient(315deg, #065f46 0%, #6ee7b7 74%);
        }
    </style>
</head>
<body class="body-bg min-h-screen pt-12 md:pt-20 pb-6 px-2 md:px-0" style="font-family:'Lato',sans-serif;">
<header class="max-w-lg mx-auto">
    <a href="#">
        <h1 class="text-4xl font-bold text-white text-center">Đa Dạng Sinh Học</h1>
    </a>
</header>

<main class="bg-white max-w-lg mx-auto p-8 md:p-12 my-10 rounded-lg shadow-2xl">
    <section>
        <h3 class="font-bold text-2xl">Chào mừng bạn trở lại</h3>
        <p class="text-gray-600 pt-2">Đây là trang quản lý</p>
    </section>
    <div th:if="${param.error}" class="bg-red-100 border border-red-400 text-red-700 px-4 py-3 rounded relative mb-4">
        Sai email hoặc mật khẩu.
    </div>
    <div th:if="${param.logout}" class="bg-green-100 border border-green-400 text-green-700 px-4 py-3 rounded relative mb-4">
        Bạn đã đăng xuất thành công.
    </div>
    <section class="mt-10">
        <form class="flex flex-col" method="POST" th:action="@{/api/auth/login}">
            <div class="mb-6 pt-3 rounded bg-gray-200">
                <label class="block text-gray-700 text-sm font-bold mb-2 ml-3" for="email">Email</label>
                <input type="text" id="email" name="email" value="admin@gmail.com" class="bg-gray-200 rounded w-full text-gray-700 focus:outline-none border-b-4 border-gray-300 focus:border-green-600 transition duration-500 px-3 pb-3">
            </div>
            <div class="mb-6 pt-3 rounded bg-gray-200">
                <label class="block text-gray-700 text-sm font-bold mb-2 ml-3" for="password">Mật khẩu</label>
                <input type="password" id="password" name="password" value="admin" class="bg-gray-200 rounded w-full text-gray-700 focus:outline-none border-b-4 border-gray-300 focus:border-green-600 transition duration-500 px-3 pb-3">
            </div>
            <div class="flex justify-end">
                <a href="#" class="text-sm text-green-600 hover:text-green-700 hover:underline mb-6">Quên mật khẩu?</a>
            </div>
            <button class="bg-green-600 hover:bg-green-700 text-white font-bold py-2 rounded shadow-lg hover:shadow-xl transition duration-200" type="submit">Đăng nhập</button>
        </form>
    </section>
</main>

<div class="max-w-lg mx-auto text-center mt-12 mb-6">
    <p class="text-white"><a href="#" class="font-bold hover:underline">Đăng ký tại đây</a>!</p>
</div>
</body>
</html>