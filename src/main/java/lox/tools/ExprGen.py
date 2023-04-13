from io import TextIOWrapper
import os


ast = {
    "Binary": "Expr left, Token op, Expr right",
    "Grouping": "Expr exp",
    "Literal": "Object value",
    "Unary": "Token op, Expr right"
}


def addType(out: TextIOWrapper, base_name: str, class_name: str, params: str):
    out.write(f"\tstatic class {class_name} extends {base_name} {{\n")
    out.write(f"\t\t{class_name} ({params}){{\n")
    for p in params.split(","):
        (type_name, param_name, *_) = p.strip().split(" ")
        out.write(f"\t\t\tthis.{param_name} = {param_name};\n")
    out.write("\t\t}\n")
    for p in params.split(","):
        out.write(f"\t\tfinal {p};\n") 
    out.write("\t}\n")

def define_ast(out_dir: str, base_name: str):
    path = os.path.join(out_dir, base_name+".java")

    with open(path, "w") as file:
        file.write("package lox;\n")
        file.write("import java.util.List;\n")
        file.write("abstract class " + base_name + " {\n")
        for class_name in ast:
            params = ast[class_name]
            addType(file, base_name, class_name, params)
        file.write("}")

if __name__ == "__main__":
    define_ast("..", "Expr")