# edn-to-json

Simple utility to parse edn into json.  Can be used from the command line
or as a library.

## Installation

```
git clone git@github.com:pladdy/edn-to-json.git
cd edn-to-json
lein bin
```

## Usage

After running `lein bin` to create the executable:
`edn2json -h` for a synopsis.

## Examples

Parse edn on the command line:
`edn2json -i '{:some "sweet edn"}'`
Will return `{"some":"sweet edn"}`

Or you can dump it to file
`edn2json -i '{:some "sweet edn"}' -o edn2json.json`
Will create a json file you can view (`less ./edn2json.json`)

