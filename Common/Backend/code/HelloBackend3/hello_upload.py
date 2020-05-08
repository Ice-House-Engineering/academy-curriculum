from flask import Flask, request, redirect, url_for
from pathlib import Path
from werkzeug.utils import secure_filename


app = Flask(__name__)

@app.route("/")
def hello_index():
    return "Hello Index!"

# https://flask.palletsprojects.com/en/1.1.x/patterns/fileuploads/
@app.route("/upload_image", methods=["GET", "POST"])
def upload_image():
    if request.method == "POST":
        if "file" not in request.files:
            return redirect(request.url)
        file = request.files["file"]
        if file.filename == "":
            return redirect(request.url)
        if file:
            filename = secure_filename(file.filename)
            uploaded_dir = Path("/tmp") / Path("uploaded_file")
            if not uploaded_dir.exists():
                uploaded_dir.mkdir()
            file.save(str(uploaded_dir / Path(filename)))
            return redirect(url_for("hello_index"))

    return '''
    <!doctype html>
    <title>Upload new File</title>
    <h1>Upload new File</h1>
    <form method=post enctype=multipart/form-data>
      <input type=file name=file>
      <input type=submit value=Upload>
    </form>
    '''
