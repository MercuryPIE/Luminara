![Luminara](https://socialify.git.ci/MercuryPIE/Luminara/image?description=1&font=JetBrains+Mono&logo=https%3A%2F%2Fraw.githubusercontent.com%2FMercuryPIE%2FLuminara%2Frefs%2Fheads%2Fmain%2FGUI%2Fsrc%2Fmain%2Fresources%2FIcons%2FLuminara-Main-Icon.svg&name=1&pattern=Circuit+Board&theme=Auto)

![GitHub Release](https://img.shields.io/github/v/release/MercuryPIE/Luminara?color=%234F76F4)

## Features
- Cross-platform support: Windows, macOS, Linux
- Lightweight runtime using `jlink`
- Easy-to-use launchers

## How to Use
1. To use Luminara just download the latest [build](https://github.com/MercuryPIE/Luminara/releases).
2. Extract the archive into a folder.
3. Run the application:
   - **Windows:** Double-click `Luminara.exe`.
   - **Linux/macOS:** TBD.
4. Select any file. 
   - **Note:** The input file must be bigger than one byte.
5. Select where to save this new `.png` file.

# Planned Features
The following features have not been implemented yet. They are planned for a future releases.
- CLI version with arguments, which will be distributed as a `.jar` file. To run it: 
```bash
$ java -jar Luminara.jar <args>
```

[//]: # (Change this section to table when features are implemented) 
### Arguments
- `-i, --input-file` Specify the input file to convert (same as selecting a file in the GUI).
- `-O, --output-dir` Specify the output directory (where to save the new file).
- `-fe, --file-extension` Allow for different file extensions, not just `.png`. Defaults to `.png` if not provided.
- `-fn, --file-name` Specify the output file name by default the file is called `luminara-output`.
- `-m, --metadata` Add metadata into the final file.
- `-k, --key` Creates a key to decode the original file; any metadata is included if the `--metadata` argument was given. 

### Example (Planned)
The following command would produce a file called `luminara-output.png` in the `~/output/` directory in the users home.
```bash
$ java -jar luminara.jar -i ~/hello-world.txt -o ~/output
```
---
The following command would produce a file called `my-cool-video.mp4` in the `~/output/` directory in the users home directory.
```bash
$ java -jar luminara.jar -i ~/hello-world.txt -o ~/output -fe mp4 -fn my-cool-video
```
---
The following command would produce two files in the `~/output/` directory in the users home directory:
- `hidden-location.jpeg`
- `hidden-location.key`
```bash
$ java -jar luminara.jar -i ~/hello-world.txt -o ~/output -fe jpeg -fn hidden-location -m "4,8,15,16,23,42" -k
```
---