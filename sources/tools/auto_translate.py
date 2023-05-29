import os.path

from googletrans import Translator
from glob import glob
import xml.etree.ElementTree as ET
from shutil import rmtree

TARGET_FILES_QUERY = "./../../**/values/strings.xml"
TARGET_LANGUAGES = ["ru", "de", "it", "ja", "ko"]

translator = Translator()

target_files = glob(TARGET_FILES_QUERY, recursive=True)

print("\nprocessing...\n")

for target_language in TARGET_LANGUAGES:
    for file_path in target_files:
        new_file = f"{os.path.dirname(file_path)}-{target_language}/strings.xml"
        print("processing", new_file)

        rmtree(os.path.dirname(new_file), ignore_errors=True)
        os.mkdir(os.path.dirname(new_file))
        with open(file_path, "r") as file:
            tree = ET.fromstring(file.read())

            for string_tag in tree.findall("string"):
                print(string_tag.text)
                string_tag.text = translator.translate(string_tag.text, dest=target_language, src="en").text
                print(string_tag.text)

            translated = ET.tostring(tree, encoding='UTF-8')
            with open(new_file, "wb") as out_file:
                out_file.write(translated)
