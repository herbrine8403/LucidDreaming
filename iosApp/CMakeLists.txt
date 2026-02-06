cmake_minimum_required(VERSION 3.15)
project(LucidDreaming VERSION 1.0.0 LANGUAGES C CXX Swift)

set(CMAKE_OSX_DEPLOYMENT_TARGET "15.0")
set(CMAKE_Swift_MODULE_DIRECTORY ${CMAKE_BINARY_DIR}/swift)
set(CMAKE_Swift_COMPILER_WORKS YES)

# Enable ARC for Objective-C
set(CMAKE_C_FLAGS "${CMAKE_C_FLAGS} -fobjc-arc")
set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -fobjc-arc")

# Find the Kotlin framework
set(KOTLIN_FRAMEWORK_PATH ${CMAKE_SOURCE_DIR}/../shared/build/cocoapods/publish/release/Shared.framework)

if(EXISTS ${KOTLIN_FRAMEWORK_PATH})
    message(STATUS "Found Kotlin framework at ${KOTLIN_FRAMEWORK_PATH}")
else()
    message(WARNING "Kotlin framework not found. Build the shared module first.")
endif()

# Add shared framework directory
include_directories(${KOTLIN_FRAMEWORK_PATH}/Headers)

# Collect Swift source files
file(GLOB_RECURSE SWIFT_SOURCES
    "LucidDreaming/*.swift"
    "LucidDreaming/**/*.swift"
)

# Create the executable
add_executable(LucidDreaming
    ${SWIFT_SOURCES}
)

# Link Swift frameworks
target_link_libraries(LucidDreaming PRIVATE
    "-framework UIKit"
    "-framework SwiftUI"
    "-framework Foundation"
    "-framework Combine"
)

# Link Kotlin framework if it exists
if(EXISTS ${KOTLIN_FRAMEWORK_PATH})
    target_link_libraries(LucidDreaming PRIVATE
        ${KOTLIN_FRAMEWORK_PATH}
    )
endif()

# Set bundle properties
set_target_properties(LucidDreaming PROPERTIES
    MACOSX_BUNDLE TRUE
    MACOSX_BUNDLE_INFO_PLIST ${CMAKE_SOURCE_DIR}/LucidDreaming/Info.plist
    MACOSX_BUNDLE_BUNDLE_NAME "LucidDreaming"
    MACOSX_BUNDLE_BUNDLE_VERSION ${PROJECT_VERSION}
    MACOSX_BUNDLE_SHORT_VERSION_STRING ${PROJECT_VERSION}
    MACOSX_BUNDLE_IDENTIFIER "com.luciddreaming.ios"
)

# Copy resources
file(GLOB_RECURSE RESOURCES
    "LucidDreaming/*.lproj"
    "LucidDreaming/*.xcassets"
    "LucidDreaming/*.storyboard"
)

foreach(resource ${RESOURCES})
    source_group("Resources" FILES ${resource})
    list(APPEND SWIFT_SOURCES ${resource})
endforeach()

target_sources(LucidDreaming PRIVATE ${RESOURCES})
set_source_files_properties(${RESOURCES} PROPERTIES MACOSX_PACKAGE_LOCATION Resources)