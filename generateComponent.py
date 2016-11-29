import sys
import os

PACKAGE_STRING = 'com.google.lecture_manager.client.components.'
CLIENT_PATH = 'src\\com\\google\\lecture_manager\\client\\'

ELEMENT_TYPES_PATH = CLIENT_PATH + 'utils\\ElementTypes.java'
COMPONENTS_PATH = CLIENT_PATH + 'components\\'
FACTORY_PATH = CLIENT_PATH + 'utils\\factories\\'
CONTROLLER_FACTORY = FACTORY_PATH + '\\ControllerFactory.java'
VIEW_FACTORY = FACTORY_PATH + 'ViewFactory.java'


def main(params):
    controller_name = params[0] + 'Controller'
    view_name = params[0] + 'View'
    create_view_and_controller(controller_name, view_name, params[1])
    if len(params) == 3:
        update_element_types(params[2])
        update_view_and_controller_factories(controller_name, view_name, params[1], params[2])


def update_element_types(enum):
    file = open(ELEMENT_TYPES_PATH, 'r')
    content = ''
    for line in file:
        if '//{new}' in line:
            content += line.replace('//{new}', '')[:-1] + ',\n'
            content += '  ' + enum + '//{new}\n'
        else:
            content += line
    file.close()
    file = open(ELEMENT_TYPES_PATH, 'w')
    file.write(content)
    file.close()


def update_view_and_controller_factories(controller_name, view_name, path, enum):
    update_view_factory(view_name, get_suffix_from_path(path), enum)
    update_controller_factory(controller_name, get_suffix_from_path(path), enum)


def update_view_factory(view_name, view_package_suffix, enum):
    file = open(VIEW_FACTORY, 'r')
    content = ''
    for line in file:
        if '{view}' in line:
            content += 'import ' + PACKAGE_STRING + view_package_suffix + '.' + view_name + ';\n'
        if '{new_case}' in line:
            content += '      case ' + enum + ':\n        return new ' + view_name + '();\n'
        content += line
    file.close()
    file = open(VIEW_FACTORY, 'w')
    file.write(content)
    file.close()


def update_controller_factory(controller_name, controller_package_suffix, enum):
    file = open(CONTROLLER_FACTORY, 'r')
    content = ''
    for line in file:
        if '{controller}' in line:
            content += 'import ' + PACKAGE_STRING + controller_package_suffix + '.' + controller_name + ';\n'
        if '{new_case}' in line:
            content += '      case ' + enum + ':\n        return ' + controller_name + '.getInstance();\n'
        content += line
    file.close()
    file = open(CONTROLLER_FACTORY, 'w')
    file.write(content)
    file.close()


def create_view_and_controller(controller_name, view_name, path):
    new_path = COMPONENTS_PATH + path
    if not os.path.isdir(new_path):
        os.makedirs(new_path)
    if not new_path.endswith('\\'):
        new_path += '\\'
    i_view_name = 'I' + view_name
    controller_path = new_path + controller_name + '.java'
    controller_string = create_controller_string(controller_name, i_view_name, path)
    view_path = new_path + view_name + '.java'
    view_string = create_view_string(controller_name, view_name, path)

    controller_file = open(controller_path, 'w')
    view_file = open(view_path, 'w')
    controller_file.write(controller_string)
    view_file.write(view_string)
    controller_file.close()
    view_file.close()


def create_controller_string(controller_name, i_view_name, path):
    controller_string = 'package ' + PACKAGE_STRING + get_suffix_from_path(path) + ';\n\n'
    controller_string += 'import com.google.lecture_manager.client.utils.Controller;\n'
    controller_string += 'import com.google.lecture_manager.client.utils.View;\n\n'
    controller_string += 'public class ' + controller_name + ' extends Controller<' + controller_name + '.' + \
                         i_view_name + '> {\n\n'
    controller_string += '  public interface ' + i_view_name + ' extends View {\n'
    controller_string += '  }\n\n'
    controller_string += '  private static ' + controller_name + ' INSTANCE = null;\n\n'
    controller_string += '  private ' + i_view_name + ' view;\n\n'
    controller_string += '  @Override\n  public void bind(' + i_view_name + ' view) {\n'
    controller_string += '    this.view = view;\n    setIsBound(true);\n'
    controller_string += '  }\n\n'
    controller_string += '  @Override\n  public View getView() {\n    return view;\n  }\n\n'
    controller_string += '  public static Controller getInstance() {\n'
    controller_string += '    if (INSTANCE == null)\n'
    controller_string += '      INSTANCE = new ' + controller_name + '();\n'
    controller_string += '    return INSTANCE;\n'
    controller_string += '  }\n}'
    return controller_string


def create_view_string(controller_name, view_name, path):
    view_string = 'package ' + PACKAGE_STRING + get_suffix_from_path(path) + ';\n\n'
    view_string += 'import com.google.gwt.user.client.ui.Widget;\n\n'
    view_string += 'public class ' + view_name + ' implements ' + controller_name + '.I' + view_name + ' {\n\n'
    view_string += '  public ' + view_name + '() {\n    initGUI();\n  }\n\n'
    view_string += '  @Override\n  public void initGUI() {\n  }\n\n'
    view_string += '  @Override\n  public Widget asWidget() {\n    return null;\n  }\n'
    view_string += '}'
    return view_string


def get_suffix_from_path(path):
    return path.replace('\\', '.') if not path.endswith('\\') else path.replace('\\', '.')[:-1]


def showinfo():
    print('[gen comp] Info:')
    print('[gen comp] To run the script -> python generateComponent.py viewName dirPath [elementType]')
    print('[gen comp] If elementType parameter is provided the following classes will be updated in order to use the'
          ' new created component:')
    print('[gen comp] \t* ElementTypes')
    print('[gen comp] \t* ControllerFactory')
    print('[gen comp] \t* ViewFactory')
    print('[gen comp] e.g. python generateComponent.py Login login\login_sub_dir')
    print('[gen comp] e.g. python generateComponent.py Login login\login_sub_dir LOGIN_FORM')


if __name__ == "__main__":
    args = sys.argv
    if len(args) < 3:
        showinfo()
        exit()
    main(args[1:])
