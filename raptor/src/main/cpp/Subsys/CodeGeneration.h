
/***************************************************************************/
/*                                                                         */
/*	CodeGeneration.h                                                       */
/*                                                                         */
/*    Raptor OpenGL & Vulkan realtime 3D Engine SDK.                       */
/*                                                                         */
/*   This file is generated by Raptor Builder application from             */
/*   Rafale Soft Inc. (c) and is necessary to compile Raptor.              */
/*   Do not modify this file, your changes would be lost on next generation*/
/*   This file defines how Raptor code is optimized according to the       */
/*	 extensions selected in Raptor Builder.                                */
/*                                                                         */
/*  Copyright 1998-2019 by                                                 */
/*  Fabrice FERRAND.                                                       */
/*                                                                         */
/*  This file is part of the Raptor project, and may only be used,         */
/*  modified, and distributed under the terms of the Raptor project        */
/*  license, LICENSE.  By continuing to use, modify, or distribute         */
/*  this file you indicate that you have read the license and              */
/*  understand and accept it fully.                                        */
/*                                                                         */
/***************************************************************************/


#if !defined(__CODE_GENERATION_H__)
#define __CODE_GENERATION_H__



#define RAPTOR_NAMESPACE_BEGIN namespace raptor { 
#define RAPTOR_NAMESPACE_END	} 
#define RAPTOR_NAMESPACE using namespace raptor; 
#define RAPTOR(name) raptor::name 

RAPTOR_NAMESPACE_BEGIN
RAPTOR_NAMESPACE_END



// define linkage specifier for OpenGL prototypes declarators 
#define DEFAULT_LINKAGE 
#define STATIC_LINKAGE	static 
#define EXTERN_LINKAGE	extern 


// define types for compatibility with Android OpenGLES 
#if defined(_ANDROID) 
	#define GLdouble double 
#endif


// Raptor Debug build
#define RAPTOR_DEBUG_MODE_GENERATION			1

// Activate CUDA PhysX framework
#define BUILD_PHYSX			1

//
//	OpenGL extensions
//
//	Mandatory extensions 
#define GL_EXT_vertex_array                 1

// OpenGL versions
#ifndef GL_VERSION_1_0
	#define	GL_VERSION_1_0		1
#endif
#ifndef GL_VERSION_1_1
	#define	GL_VERSION_1_1		1
#endif
#ifndef GL_VERSION_1_2
	#define	GL_VERSION_1_2		1
#endif
#ifndef GL_VERSION_1_3
	#define	GL_VERSION_1_3		1
#endif
#ifndef GL_VERSION_1_4
	#define	GL_VERSION_1_4		1
#endif
#ifndef GL_VERSION_1_5
	#define	GL_VERSION_1_5		1
#endif
#ifndef GL_VERSION_2_0
	#define	GL_VERSION_2_0		1
#endif
#ifndef GL_VERSION_2_1
	#define	GL_VERSION_2_1		1
#endif
#ifndef GL_VERSION_3_0
	#define	GL_VERSION_3_0		1
#endif
#ifndef GL_VERSION_3_1
	#define	GL_VERSION_3_1		1
#endif
#ifndef GL_VERSION_3_2
	#define	GL_VERSION_3_2		1
#endif
#ifndef GL_VERSION_3_3
	#define	GL_VERSION_3_3		1
#endif
#ifndef GL_VERSION_4_0
	#define	GL_VERSION_4_0		1
#endif
#ifndef GL_VERSION_4_1
	#define	GL_VERSION_4_1		1
#endif
#ifndef GL_VERSION_4_2
	#define	GL_VERSION_4_2		1
#endif
#ifndef GL_VERSION_4_3
	#define	GL_VERSION_4_3		1
#endif
#ifndef GL_VERSION_4_4
	#define	GL_VERSION_4_4		1
#endif
#ifndef GL_VERSION_4_5
	#define	GL_VERSION_4_5		1
#endif
#ifndef GL_VERSION_4_6
	#define	GL_VERSION_4_6		1
#endif
#ifndef VK_VERSION_1_0
	#define	VK_VERSION_1_0		1
#endif

#define	RAPTOR_SMP_CODE_GENERATION_EXTENSION_NAME "RAPTOR_SMP_CODE_GENERATION"
#define	RAPTOR_SSE_CODE_GENERATION_EXTENSION_NAME "RAPTOR_SSE_CODE_GENERATION"
#define	RAPTOR_SSE2_CODE_GENERATION_EXTENSION_NAME "RAPTOR_SSE2_CODE_GENERATION"
#define	RAPTOR_SSE3_CODE_GENERATION_EXTENSION_NAME "RAPTOR_SSE3_CODE_GENERATION"
#define	RAPTOR_SSSE3_CODE_GENERATION_EXTENSION_NAME "RAPTOR_SSSE3_CODE_GENERATION"
#define	RAPTOR_SSE41_CODE_GENERATION_EXTENSION_NAME "RAPTOR_SSE41_CODE_GENERATION"
#define	RAPTOR_SSE42_CODE_GENERATION_EXTENSION_NAME "RAPTOR_SSE42_CODE_GENERATION"
#define	RAPTOR_AES_CODE_GENERATION_EXTENSION_NAME "RAPTOR_AES_CODE_GENERATION"
#define	RAPTOR_AVX_CODE_GENERATION_EXTENSION_NAME "RAPTOR_AVX_CODE_GENERATION"
#define	RAPTOR_FMA_CODE_GENERATION_EXTENSION_NAME "RAPTOR_FMA_CODE_GENERATION"
#define	GL_VERSION_1_0_EXTENSION_NAME "GL_VERSION_1_0"
#define	GL_VERSION_1_1_EXTENSION_NAME "GL_VERSION_1_1"
#define	GL_VERSION_1_2_EXTENSION_NAME "GL_VERSION_1_2"
#define	GL_VERSION_1_3_EXTENSION_NAME "GL_VERSION_1_3"
#define	GL_VERSION_1_4_EXTENSION_NAME "GL_VERSION_1_4"
#define	GL_VERSION_1_5_EXTENSION_NAME "GL_VERSION_1_5"
#define	GL_VERSION_2_0_EXTENSION_NAME "GL_VERSION_2_0"
#define	GL_VERSION_2_1_EXTENSION_NAME "GL_VERSION_2_1"
#define	GL_VERSION_3_0_EXTENSION_NAME "GL_VERSION_3_0"
#define	GL_VERSION_3_1_EXTENSION_NAME "GL_VERSION_3_1"
#define	GL_VERSION_3_2_EXTENSION_NAME "GL_VERSION_3_2"
#define	GL_VERSION_3_3_EXTENSION_NAME "GL_VERSION_3_3"
#define	GL_VERSION_4_0_EXTENSION_NAME "GL_VERSION_4_0"
#define	GL_VERSION_4_1_EXTENSION_NAME "GL_VERSION_4_1"
#define	GL_VERSION_4_2_EXTENSION_NAME "GL_VERSION_4_2"
#define	GL_VERSION_4_3_EXTENSION_NAME "GL_VERSION_4_3"
#define	GL_VERSION_4_4_EXTENSION_NAME "GL_VERSION_4_4"
#define	GL_VERSION_4_5_EXTENSION_NAME "GL_VERSION_4_5"
#define	GL_VERSION_4_6_EXTENSION_NAME "GL_VERSION_4_6"
#define	GL_CORE_PROFILE_EXTENSION_NAME "GL_CORE_profile"
#define	GL_COMPATIBILITY_PROFILE_EXTENSION_NAME "GL_COMPATIBILITY_profile"
#define	GL_FULL_PROFILE_EXTENSION_NAME "GL_FULL_profile"
#ifndef GL_FULL_profile
	#define GL_FULL_profile				1
#endif
#define	GL_ARB_MULTITEXTURE_EXTENSION_NAME "GL_ARB_multitexture"
#ifndef GL_ARB_multitexture
	#define GL_ARB_multitexture				1
#endif
#define	GL_ARB_TEXTURE_MIRRORED_REPEAT_EXTENSION_NAME "GL_ARB_texture_mirrored_repeat"
#ifndef GL_ARB_texture_mirrored_repeat
	#define GL_ARB_texture_mirrored_repeat				1
#endif
#define	GL_ARB_TRANSPOSE_MATRIX_EXTENSION_NAME "GL_ARB_transpose_matrix"
#ifndef GL_ARB_transpose_matrix
	#define GL_ARB_transpose_matrix				1
#endif
#define	GL_ARB_IMAGING_EXTENSION_NAME "GL_ARB_imaging"
#ifndef GL_ARB_imaging
	#define GL_ARB_imaging				1
#endif
#define	GL_ARB_TEXTURE_CUBE_MAP_EXTENSION_NAME "GL_ARB_texture_cube_map"
#ifndef GL_ARB_texture_cube_map
	#define GL_ARB_texture_cube_map				1
#endif
#define	GL_ARB_VERTEX_BUFFER_OBJECT_EXTENSION_NAME "GL_ARB_vertex_buffer_object"
#ifndef GL_ARB_vertex_buffer_object
	#define GL_ARB_vertex_buffer_object				1
#endif
#define	GL_ARB_FRAGMENT_PROGRAM_EXTENSION_NAME "GL_ARB_fragment_program"
#ifndef GL_ARB_fragment_program
	#define GL_ARB_fragment_program				1
#endif
#define	GL_ARB_OCCLUSION_QUERY_EXTENSION_NAME "GL_ARB_occlusion_query"
#ifndef GL_ARB_occlusion_query
	#define GL_ARB_occlusion_query				1
#endif
#define	GL_ARB_POINT_SPRITE_EXTENSION_NAME "GL_ARB_point_sprite"
#ifndef GL_ARB_point_sprite
	#define GL_ARB_point_sprite				1
#endif
#define	GL_ARB_POINT_PARAMETERS_EXTENSION_NAME "GL_ARB_point_parameters"
#ifndef GL_ARB_point_parameters
	#define GL_ARB_point_parameters				1
#endif
#define	GL_ARB_SHADOW_AMBIENT_EXTENSION_NAME "GL_ARB_shadow_ambient"
#define	GL_ARB_DEPTH_TEXTURE_EXTENSION_NAME "GL_ARB_depth_texture"
#ifndef GL_ARB_depth_texture
	#define GL_ARB_depth_texture				1
#endif
#define	GL_ARB_VERTEX_PROGRAM_EXTENSION_NAME "GL_ARB_vertex_program"
#ifndef GL_ARB_vertex_program
	#define GL_ARB_vertex_program				1
#endif
#define	GL_ARB_TEXTURE_NON_POWER_OF_TWO_EXTENSION_NAME "GL_ARB_texture_non_power_of_two"
#ifndef GL_ARB_texture_non_power_of_two
	#define GL_ARB_texture_non_power_of_two				1
#endif
#define	GL_ARB_TEXTURE_FLOAT_EXTENSION_NAME "GL_ARB_texture_float"
#ifndef GL_ARB_texture_float
	#define GL_ARB_texture_float				1
#endif
#define	GL_ARB_TEXTURE_BORDER_CLAMP_EXTENSION_NAME "GL_ARB_texture_border_clamp"
#ifndef GL_ARB_texture_border_clamp
	#define GL_ARB_texture_border_clamp				1
#endif
#define	GL_ARB_SHADER_OBJECTS_EXTENSION_NAME "GL_ARB_shader_objects"
#ifndef GL_ARB_shader_objects
	#define GL_ARB_shader_objects				1
#endif
#define	GL_ARB_HALF_FLOAT_PIXEL_EXTENSION_NAME "GL_ARB_half_float_pixel"
#ifndef GL_ARB_half_float_pixel
	#define GL_ARB_half_float_pixel				1
#endif
#define	GL_ARB_WINDOW_POS_EXTENSION_NAME "GL_ARB_window_pos"
#ifndef GL_ARB_window_pos
	#define GL_ARB_window_pos				1
#endif
#define	GL_ARB_VERTEX_ARRAY_OBJECT_EXTENSION_NAME "GL_ARB_vertex_array_object"
#ifndef GL_ARB_vertex_array_object
	#define GL_ARB_vertex_array_object				1
#endif
#define	GL_ARB_TEXTURE_COMPRESSION_EXTENSION_NAME "GL_ARB_texture_compression"
#ifndef GL_ARB_texture_compression
	#define GL_ARB_texture_compression				1
#endif
#define	GL_ARB_TEXTURE_MIRROR_CLAMP_TO_EDGE_EXTENSION_NAME "GL_ARB_texture_mirror_clamp_to_edge"
#ifndef GL_ARB_texture_mirror_clamp_to_edge
	#define GL_ARB_texture_mirror_clamp_to_edge				1
#endif
#define	GL_ARB_TEXTURE_ENV_COMBINE_EXTENSION_NAME "GL_ARB_texture_env_combine"
#ifndef GL_ARB_texture_env_combine
	#define GL_ARB_texture_env_combine				1
#endif
#define	GL_ARB_SHADOW_EXTENSION_NAME "GL_ARB_shadow"
#ifndef GL_ARB_shadow
	#define GL_ARB_shadow				1
#endif
#define	GL_ARB_PIXEL_BUFFER_OBJECT_EXTENSION_NAME "GL_ARB_pixel_buffer_object"
#ifndef GL_ARB_pixel_buffer_object
	#define GL_ARB_pixel_buffer_object				1
#endif
#define	GL_ARB_TEXTURE_RECTANGLE_EXTENSION_NAME "GL_ARB_texture_rectangle"
#ifndef GL_ARB_texture_rectangle
	#define GL_ARB_texture_rectangle				1
#endif
#define	GL_ARB_VERTEX_SHADER_EXTENSION_NAME "GL_ARB_vertex_shader"
#ifndef GL_ARB_vertex_shader
	#define GL_ARB_vertex_shader				1
#endif
#define	GL_ARB_FRAGMENT_SHADER_EXTENSION_NAME "GL_ARB_fragment_shader"
#ifndef GL_ARB_fragment_shader
	#define GL_ARB_fragment_shader				1
#endif
#define	GL_ARB_TEXTURE_COMPRESSION_RGTC_EXTENSION_NAME "GL_ARB_texture_compression_rgtc"
#ifndef GL_ARB_texture_compression_rgtc
	#define GL_ARB_texture_compression_rgtc				1
#endif
#define	GL_ARB_TEXTURE_RG_EXTENSION_NAME "GL_ARB_texture_rg"
#ifndef GL_ARB_texture_rg
	#define GL_ARB_texture_rg				1
#endif
#define	GL_ARB_FRAGMENT_COORD_CONVENTIONS_EXTENSION_NAME "GL_ARB_fragment_coord_conventions"
#ifndef GL_ARB_fragment_coord_conventions
	#define GL_ARB_fragment_coord_conventions				1
#endif
#define	GL_ARB_GEOMETRY_SHADER4_EXTENSION_NAME "GL_ARB_geometry_shader4"
#ifndef GL_ARB_geometry_shader4
	#define GL_ARB_geometry_shader4				1
#endif
#define	GL_ARB_COMPRESSED_TEXTURE_PIXEL_STORAGE_EXTENSION_NAME "GL_ARB_compressed_texture_pixel_storage"
#ifndef GL_ARB_compressed_texture_pixel_storage
	#define GL_ARB_compressed_texture_pixel_storage				1
#endif
#define	GL_ARB_DRAW_BUFFERS_EXTENSION_NAME "GL_ARB_draw_buffers"
#ifndef GL_ARB_draw_buffers
	#define GL_ARB_draw_buffers				1
#endif
#define	GL_ARB_COLOR_BUFFER_FLOAT_EXTENSION_NAME "GL_ARB_color_buffer_float"
#ifndef GL_ARB_color_buffer_float
	#define GL_ARB_color_buffer_float				1
#endif
#define	GL_ARB_MULTISAMPLE_EXTENSION_NAME "GL_ARB_multisample"
#ifndef GL_ARB_multisample
	#define GL_ARB_multisample				1
#endif
#define	GL_ARB_TEXTURE_ENV_DOT3_EXTENSION_NAME "GL_ARB_texture_env_dot3"
#ifndef GL_ARB_texture_env_dot3
	#define GL_ARB_texture_env_dot3				1
#endif
#define	GL_ARB_FRAGMENT_PROGRAM_SHADOW_EXTENSION_NAME "GL_ARB_fragment_program_shadow"
#ifndef GL_ARB_fragment_program_shadow
	#define GL_ARB_fragment_program_shadow				1
#endif
#define	GL_ARB_TEXTURE_COMPRESSION_BPTC_EXTENSION_NAME "GL_ARB_texture_compression_bptc"
#ifndef GL_ARB_texture_compression_bptc
	#define GL_ARB_texture_compression_bptc				1
#endif
#define	GL_ARB_UNIFORM_BUFFER_OBJECT_EXTENSION_NAME "GL_ARB_uniform_buffer_object"
#ifndef GL_ARB_uniform_buffer_object
	#define GL_ARB_uniform_buffer_object				1
#endif
#define	GL_ARB_COMPATIBILITY_EXTENSION_NAME "GL_ARB_compatibility"
#ifndef GL_ARB_compatibility
	#define GL_ARB_compatibility				1
#endif
#define	GL_ARB_DEBUG_OUTPUT_EXTENSION_NAME "GL_ARB_debug_output"
#ifndef GL_ARB_debug_output
	#define GL_ARB_debug_output				1
#endif
#define	GL_ARB_SHADING_LANGUAGE_100_EXTENSION_NAME "GL_ARB_shading_language_100"
#ifndef GL_ARB_shading_language_100
	#define GL_ARB_shading_language_100				1
#endif
#define	VK_VERSION_1_0_EXTENSION_NAME "VK_VERSION_1_0"
#define	VK_KHR_SURFACE_EXTENSION_NAME "VK_KHR_surface"
#ifndef VK_KHR_surface
	#define VK_KHR_surface				1
#endif
#define	VK_KHR_SWAPCHAIN_EXTENSION_NAME "VK_KHR_swapchain"
#ifndef VK_KHR_swapchain
	#define VK_KHR_swapchain				1
#endif
#define	VK_KHR_DISPLAY_EXTENSION_NAME "VK_KHR_display"
#define	VK_KHR_DISPLAY_SWAPCHAIN_EXTENSION_NAME "VK_KHR_display_swapchain"
#define	VK_KHR_XLIB_SURFACE_EXTENSION_NAME "VK_KHR_xlib_surface"
#define	VK_KHR_XCB_SURFACE_EXTENSION_NAME "VK_KHR_xcb_surface"
#define	VK_KHR_WAYLAND_SURFACE_EXTENSION_NAME "VK_KHR_wayland_surface"
#define	VK_KHR_MIR_SURFACE_EXTENSION_NAME "VK_KHR_mir_surface"
#define	VK_KHR_ANDROID_SURFACE_EXTENSION_NAME "VK_KHR_android_surface"
#define	VK_KHR_WIN32_SURFACE_EXTENSION_NAME "VK_KHR_win32_surface"
#ifndef VK_KHR_win32_surface
	#define VK_KHR_win32_surface				1
#endif
#define	VK_EXT_DEBUG_REPORT_EXTENSION_NAME "VK_EXT_debug_report"
#ifndef VK_EXT_debug_report
	#define VK_EXT_debug_report				1
#endif
#define	VK_KHR_SAMPLER_MIRROR_CLAMP_TO_EDGE_EXTENSION_NAME "VK_KHR_sampler_mirror_clamp_to_edge"
#ifndef VK_KHR_sampler_mirror_clamp_to_edge
	#define VK_KHR_sampler_mirror_clamp_to_edge				1
#endif
#define	GL_3DFX_TEXTURE_COMPRESSION_FXT1_EXTENSION_NAME "GL_3DFX_texture_compression_FXT1"
#define	GL_EXT_ABGR_EXTENSION_NAME "GL_EXT_abgr"
#ifndef GL_EXT_abgr
	#define GL_EXT_abgr				1
#endif
#define	GL_EXT_BGRA_EXTENSION_NAME "GL_EXT_bgra"
#ifndef GL_EXT_bgra
	#define GL_EXT_bgra				1
#endif
#define	GL_EXT_CMYKA_EXTENSION_NAME "GL_EXT_cmyka"
#define	GL_EXT_CLIP_VOLUME_HINT_EXTENSION_NAME "GL_EXT_clip_volume_hint"
#define	GL_EXT_COMPILED_VERTEX_ARRAY_EXTENSION_NAME "GL_EXT_compiled_vertex_array"
#ifndef GL_EXT_compiled_vertex_array
	#define GL_EXT_compiled_vertex_array				1
#endif
#define	GL_EXT_CULL_VERTEX_EXTENSION_NAME "GL_EXT_cull_vertex"
#define	GL_EXT_PACKED_PIXELS_EXTENSION_NAME "GL_EXT_packed_pixels"
#ifndef GL_EXT_packed_pixels
	#define GL_EXT_packed_pixels				1
#endif
#define	GL_EXT_POINT_PARAMETERS_EXTENSION_NAME "GL_EXT_point_parameters"
#ifndef GL_EXT_point_parameters
	#define GL_EXT_point_parameters				1
#endif
#define	GL_EXT_STENCIL_WRAP_EXTENSION_NAME "GL_EXT_stencil_wrap"
#ifndef GL_EXT_stencil_wrap
	#define GL_EXT_stencil_wrap				1
#endif
#define	GL_EXT_STENCIL_TWO_SIDE_EXTENSION_NAME "GL_EXT_stencil_two_side"
#ifndef GL_EXT_stencil_two_side
	#define GL_EXT_stencil_two_side				1
#endif
#define	GL_EXT_TEXTURE_ENV_ADD_EXTENSION_NAME "GL_EXT_texture_env_add"
#ifndef GL_EXT_texture_env_add
	#define GL_EXT_texture_env_add				1
#endif
#define	GL_EXT_SECONDARY_COLOR_EXTENSION_NAME "GL_EXT_secondary_color"
#ifndef GL_EXT_secondary_color
	#define GL_EXT_secondary_color				1
#endif
#define	GL_EXT_SEPARATE_SPECULAR_COLOR_EXTENSION_NAME "GL_EXT_separate_specular_color"
#ifndef GL_EXT_separate_specular_color
	#define GL_EXT_separate_specular_color				1
#endif
#define	GL_EXT_VERTEX_WEIGHTING_EXTENSION_NAME "GL_EXT_vertex_weighting"
#define	GL_EXT_TEXTURE_FILTER_ANISOTROPIC_EXTENSION_NAME "GL_EXT_texture_filter_anisotropic"
#ifndef GL_EXT_texture_filter_anisotropic
	#define GL_EXT_texture_filter_anisotropic				1
#endif
#define	GL_EXT_FOG_COORD_EXTENSION_NAME "GL_EXT_fog_coord"
#ifndef GL_EXT_fog_coord
	#define GL_EXT_fog_coord				1
#endif
#define	GL_EXT_TEXTURE3D_EXTENSION_NAME "GL_EXT_texture3D"
#ifndef GL_EXT_texture3D
	#define GL_EXT_texture3D				1
#endif
#define	GL_EXT_TEXTURE_EDGE_CLAMP_EXTENSION_NAME "GL_EXT_texture_edge_clamp"
#ifndef GL_EXT_texture_edge_clamp
	#define GL_EXT_texture_edge_clamp				1
#endif
#define	GL_EXT_BLEND_COLOR_EXTENSION_NAME "GL_EXT_blend_color"
#ifndef GL_EXT_blend_color
	#define GL_EXT_blend_color				1
#endif
#define	GL_EXT_TEXTURE_COMPRESSION_LATC_EXTENSION_NAME "GL_EXT_texture_compression_latc"
#ifndef GL_EXT_texture_compression_latc
	#define GL_EXT_texture_compression_latc				1
#endif
#define	GL_EXT_TEXTURE_COMPRESSION_S3TC_EXTENSION_NAME "GL_EXT_texture_compression_s3tc"
#ifndef GL_EXT_texture_compression_s3tc
	#define GL_EXT_texture_compression_s3tc				1
#endif
#define	GL_EXT_TEXTURE_COMPRESSION_DXT1_EXTENSION_NAME "GL_EXT_texture_compression_dxt1"
#ifndef GL_EXT_texture_compression_dxt1
	#define GL_EXT_texture_compression_dxt1				1
#endif
#define	GL_EXT_FRAMEBUFFER_OBJECT_EXTENSION_NAME "GL_EXT_framebuffer_object"
#ifndef GL_EXT_framebuffer_object
	#define GL_EXT_framebuffer_object				1
#endif
#define	GL_EXT_FRAMEBUFFER_BLIT_EXTENSION_NAME "GL_EXT_framebuffer_blit"
#ifndef GL_EXT_framebuffer_blit
	#define GL_EXT_framebuffer_blit				1
#endif
#define	GL_EXT_FRAMEBUFFER_MULTISAMPLE_EXTENSION_NAME "GL_EXT_framebuffer_multisample"
#ifndef GL_EXT_framebuffer_multisample
	#define GL_EXT_framebuffer_multisample				1
#endif
#define	GL_EXT_PACKED_DEPTH_STENCIL_EXTENSION_NAME "GL_EXT_packed_depth_stencil"
#ifndef GL_EXT_packed_depth_stencil
	#define GL_EXT_packed_depth_stencil				1
#endif
#define	GL_NV_TEXGEN_REFLECTION_EXTENSION_NAME "GL_NV_texgen_reflection"
#ifndef GL_NV_texgen_reflection
	#define GL_NV_texgen_reflection				1
#endif
#define	GL_NV_TEXTURE_RECTANGLE_EXTENSION_NAME "GL_NV_texture_rectangle"
#ifndef GL_NV_texture_rectangle
	#define GL_NV_texture_rectangle				1
#endif
#define	GL_NV_VERTEX_ARRAY_RANGE_EXTENSION_NAME "GL_NV_vertex_array_range"
#ifndef GL_NV_vertex_array_range
	#define GL_NV_vertex_array_range				1
#endif
#define	GL_NV_POINT_SPRITE_EXTENSION_NAME "GL_NV_point_sprite"
#ifndef GL_NV_point_sprite
	#define GL_NV_point_sprite				1
#endif
#define	GL_NV_REGISTER_COMBINERS_EXTENSION_NAME "GL_NV_register_combiners"
#ifndef GL_NV_register_combiners
	#define GL_NV_register_combiners				1
#endif
#define	GL_NV_TEXTURE_ENV_COMBINE4_EXTENSION_NAME "GL_NV_texture_env_combine4"
#ifndef GL_NV_texture_env_combine4
	#define GL_NV_texture_env_combine4				1
#endif
#define	GL_NV_FRAGMENT_PROGRAM_EXTENSION_NAME "GL_NV_fragment_program"
#ifndef GL_NV_fragment_program
	#define GL_NV_fragment_program				1
#endif
#define	GL_NV_TEXTURE_SHADER_EXTENSION_NAME "GL_NV_texture_shader"
#ifndef GL_NV_texture_shader
	#define GL_NV_texture_shader				1
#endif
#define	GL_NV_TEXTURE_COMPRESSION_VTC_EXTENSION_NAME "GL_NV_texture_compression_vtc"
#ifndef GL_NV_texture_compression_vtc
	#define GL_NV_texture_compression_vtc				1
#endif
#define	GL_NV_FLOAT_BUFFER_EXTENSION_NAME "GL_NV_float_buffer"
#ifndef GL_NV_float_buffer
	#define GL_NV_float_buffer				1
#endif
#define	GL_WIN_SWAP_HINT_EXTENSION_NAME "GL_WIN_swap_hint"
#ifndef GL_WIN_swap_hint
	#define GL_WIN_swap_hint				1
#endif
#define	WGL_ARB_EXTENSIONS_STRING_EXTENSION_NAME "WGL_ARB_extensions_string"
#ifndef WGL_ARB_extensions_string
	#define WGL_ARB_extensions_string				1
#endif
#define	WGL_NV_RENDER_TEXTURE_RECTANGLE_EXTENSION_NAME "WGL_NV_render_texture_rectangle"
#ifndef WGL_NV_render_texture_rectangle
	#define WGL_NV_render_texture_rectangle				1
#endif
#define	WGL_EXT_SWAP_CONTROL_EXTENSION_NAME "WGL_EXT_swap_control"
#ifndef WGL_EXT_swap_control
	#define WGL_EXT_swap_control				1
#endif
#define	WGL_ARB_PIXEL_FORMAT_EXTENSION_NAME "WGL_ARB_pixel_format"
#ifndef WGL_ARB_pixel_format
	#define WGL_ARB_pixel_format				1
#endif
#define	WGL_NV_RENDER_DEPTH_TEXTURE_EXTENSION_NAME "WGL_NV_render_depth_texture"
#ifndef WGL_NV_render_depth_texture
	#define WGL_NV_render_depth_texture				1
#endif
#define	WGL_EXT_SWAP_CONTROL_TEAR_EXTENSION_NAME "WGL_EXT_swap_control_tear"
#ifndef WGL_EXT_swap_control_tear
	#define WGL_EXT_swap_control_tear				1
#endif
#define	WGL_ARB_PBUFFER_EXTENSION_NAME "WGL_ARB_pbuffer"
#ifndef WGL_ARB_pbuffer
	#define WGL_ARB_pbuffer				1
#endif
#define	WGL_ARB_CREATE_CONTEXT_EXTENSION_NAME "WGL_ARB_create_context"
#ifndef WGL_ARB_create_context
	#define WGL_ARB_create_context				1
#endif
#define	WGL_ARB_CREATE_CONTEXT_PROFILE_EXTENSION_NAME "WGL_ARB_create_context_profile"
#ifndef WGL_ARB_create_context_profile
	#define WGL_ARB_create_context_profile				1
#endif
#define	WGL_ARB_RENDER_TEXTURE_EXTENSION_NAME "WGL_ARB_render_texture"
#ifndef WGL_ARB_render_texture
	#define WGL_ARB_render_texture				1
#endif
#define	GLX_EXTENSIONS_EXTENSION_NAME "GLX_EXTENSIONS"
#define	WGL_ATI_PIXEL_FORMAT_FLOAT_EXTENSION_NAME "WGL_ATI_pixel_format_float"
#ifndef WGL_ATI_pixel_format_float
	#define WGL_ATI_pixel_format_float				1
#endif
#define	GL_KHR_TEXTURE_COMPRESSION_ASTC_HDR_EXTENSION_NAME "GL_KHR_texture_compression_astc_hdr"
#define	GL_KHR_TEXTURE_COMPRESSION_ASTC_LDR_EXTENSION_NAME "GL_KHR_texture_compression_astc_ldr"
#define	GL_OES_COMPRESSED_PALETTED_TEXTURE_EXTENSION_NAME "GL_OES_compressed_paletted_texture"


//	CPU code generation

#define RAPTOR_SMP_CODE_GENERATION				1
#define RAPTOR_SSE_CODE_GENERATION				1
#define RAPTOR_SSE2_CODE_GENERATION				1
#define RAPTOR_SSE3_CODE_GENERATION				1
#define RAPTOR_SSSE3_CODE_GENERATION				1
#define RAPTOR_SSE41_CODE_GENERATION				1
#define RAPTOR_SSE42_CODE_GENERATION				1
#define RAPTOR_AES_CODE_GENERATION				1
#define RAPTOR_AVX_CODE_GENERATION				1

#define __SSE__  1
#define __SSE2__  1
#define __SSE3__  1
#define __SSSE3__  1
#define __SSE4_1__  1
#define __SSE4_2__  1

#include <immintrin.h>

#include "Portability.h"


#endif
