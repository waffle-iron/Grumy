git add .
git commit -m "Upload"
git remote remove origin
git remote add origin https://gitlab.com/Plexian/Pendulum.git
git push -u origin master --force
