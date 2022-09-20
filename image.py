import ddddocr


def image():
    ocr = ddddocr.Ddddocr()
    with open("G:/Download/code.jpg", 'rb') as f:
        img_bytes = f.read()
    res = ocr.classification(img_bytes).upper()
    return res


image()
