<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
    <!DOCTYPE html>
    <html>
    <head>
        <link rel="stylesheet" href="/styles/styles.css">
        <meta charset="UTF-8">
    </head>
    <body>
    <div class="container">
        <div class="login">
            <form action="login" method="post">
                <h1 class="title">Bejelentkezés</h1>

                <label for="username" class="body">Felhasználónév</label><br>
                <input type="text" name="username" required><br>

                <label for="password" class="body">Jelszó</label><br>
                <input type="password" name="password" required><br>

                <button type="submit">Bejelentkezés</button>

            </form>
        </div>
        <div class="dropdown-reg">
            <button onclick="dropdown()" class="or">
                Ha nincs még fiókja, <b>regisztráljon</b> egyet most!
            </button>
            <div id="reg" class="signup">
                <form action="registration" method="post">
                    <h1 class="title">Regisztráció</h1>

                    <label for="username" class="body">Felhasználónév</label><br>
                    <input type="text" name="username" required><br>

                    <label for="fullname" class="body">Név</label><br>
                    <input type="text" name="fullname" required><br>

                    <label for="email" class="body">Email</label><br>
                    <input type="text" name="email" required><br>

                    <label for="password" class="body">Jelszó</label><br>
                    <input type="password" name="password" required><br>

                    <label for="password-re" class="body">Jelszó ismét</label><br>
                    <input type="password" name="password-re" required><br>

                    <label for="privacy" class="body">
                        <input type="checkbox" name="privacy" required>Elfogadom az <a href="#">adatvédelmi szabályzatot</a>.<br>
                    </label>

                    <button type="submit">Regisztráció</button>

                </form>
            </div>
        </div>
        <script>
            function dropdown() {
                document.getElementById("reg").classList.toggle("show");
            }
        </script>
    </div>
    </body>
    </html>